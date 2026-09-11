package com.pragma.integracion.domain.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HexFormat;
import java.util.Objects;
import java.util.regex.Pattern;

public final class IdempotenciaKey {

    private static final String PREFIX_CREDITO = "CRE";
    private static final String PREFIX_DEBITO = "DEB";
    private static final String SEPARATOR = "|";
    private static final int MAX_KEY_LENGTH = 128;
    private static final Pattern VALID_FORMAT_PATTERN = Pattern.compile("^(CRE|DEB)\\|.+\\|.+\\|\\d{4}-\\d{2}-\\d{2}$");

    private final String claveOriginal;
    private final String claveHash;
    private final TipoOperacion tipoOperacion;
    private final String identificadorNegocio;
    private final LocalDate fechaOperacion;

    private IdempotenciaKey(String claveOriginal, String claveHash, TipoOperacion tipoOperacion, 
                           String identificadorNegocio, LocalDate fechaOperacion) {
        this.claveOriginal = Objects.requireNonNull(claveOriginal, "La clave original no puede ser nula");
        this.claveHash = Objects.requireNonNull(claveHash, "El hash no puede ser nulo");
        this.tipoOperacion = Objects.requireNonNull(tipoOperacion, "El tipo de operación no puede ser nulo");
        this.identificadorNegocio = Objects.requireNonNull(identificadorNegocio, "El identificador de negocio no puede ser nulo");
        this.fechaOperacion = Objects.requireNonNull(fechaOperacion, "La fecha de operación no puede ser nula");
    }

    public static IdempotenciaKey crear(EventoBancario evento) {
        Objects.requireNonNull(evento, "El evento no puede ser nulo");
        
        String prefix = evento.esCredito() ? PREFIX_CREDITO : PREFIX_DEBITO;
        String identificador = evento.clienteId() + SEPARATOR + evento.cuentaOrigen();
        
        if (evento.cuentaDestino() != null && !evento.cuentaDestino().isEmpty()) {
            identificador += SEPARATOR + evento.cuentaDestino();
        }
        
        String claveOriginal = buildClaveOriginal(prefix, identificador, evento.fechaCreacion().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        String claveHash = generarHash(claveOriginal);
        
        return new IdempotenciaKey(
            claveOriginal,
            claveHash,
            evento.tipoOperacion(),
            identificador,
            evento.fechaCreacion().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
        );
    }

    public static IdempotenciaKey desdeTexto(String clave) {
        if (clave == null || clave.isEmpty()) {
            throw new IllegalArgumentException("La clave de idempotencia no puede ser nula o vacía");
        }
        
        if (clave.length() > MAX_KEY_LENGTH) {
            throw new IllegalArgumentException("La clave de idempotencia excede el longitud máxima permitida de " + MAX_KEY_LENGTH);
        }
        
        if (!VALID_FORMAT_PATTERN.matcher(clave).matches()) {
            throw new IllegalArgumentException("El formato de la clave de idempotencia es inválido: " + clave);
        }
        
        String[] partes = clave.split("\\" + SEPARATOR);
        TipoOperacion tipo = partes[0].equals(PREFIX_CREDITO) ? TipoOperacion.CREDITO : TipoOperacion.DEBITO;
        String identificador = partes[1];
        LocalDate fecha = LocalDate.parse(partes[2], DateTimeFormatter.ISO_LOCAL_DATE);
        
        String claveHash = generarHash(clave);
        
        return new IdempotenciaKey(clave, claveHash, tipo, identificador, fecha);
    }

    public boolean esDuplicado(IdempotenciaKey otraClave) {
        if (otraClave == null) {
            return false;
        }
        return this.claveHash.equals(otraClave.claveHash) || 
               this.claveOriginal.equals(otraClave.claveOriginal);
    }

    public boolean esDelMismoDia(IdempotenciaKey otraClave) {
        if (otraClave == null) {
            return false;
        }
        return this.fechaOperacion.equals(otraClave.fechaOperacion);
    }

    public boolean esDelMismoTipoOperacion(IdempotenciaKey otraClave) {
        if (otraClave == null) {
            return false;
        }
        return this.tipoOperacion == otraClave.tipoOperacion;
    }

    public boolean perteneceAlMismoCliente(String clienteId) {
        if (clienteId == null || clienteId.isEmpty()) {
            return false;
        }
        return this.identificadorNegocio.startsWith(clienteId + SEPARATOR);
    }

    public String getClaveOriginal() {
        return claveOriginal;
    }

    public String getClaveHash() {
        return claveHash;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public String getIdentificadorNegocio() {
        return identificadorNegocio;
    }

    public LocalDate getFechaOperacion() {
        return fechaOperacion;
    }

    public String getClaveParaPersistencia() {
        return claveHash.length() < claveOriginal.length() ? claveOriginal : claveHash;
    }

    private static String buildClaveOriginal(String prefix, String identificador, LocalDate fecha) {
        String fechaStr = fecha.format(DateTimeFormatter.ISO_LOCAL_DATE);
        return prefix + SEPARATOR + identificador + SEPARATOR + fechaStr;
    }

    private static String generarHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hashBytes).substring(0, 32).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Algoritmo SHA-256 no disponible en el entorno", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdempotenciaKey that = (IdempotenciaKey) o;
        return Objects.equals(claveOriginal, that.claveOriginal) || 
               Objects.equals(claveHash, that.claveHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(claveOriginal, claveHash);
    }

    @Override
    public String toString() {
        return "IdempotenciaKey{hash='" + claveHash + "', tipo=" + tipoOperacion + "}";
    }
}