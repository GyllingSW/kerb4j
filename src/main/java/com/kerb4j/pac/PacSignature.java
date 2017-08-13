package com.kerb4j.pac;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.kerb4j.Kerb4JException;

public class PacSignature {

    private int type;
    private byte[] checksum;

    public PacSignature(byte[] data) throws Kerb4JException {
        try {
            PacDataInputStream bufferStream = new PacDataInputStream(new DataInputStream(
                    new ByteArrayInputStream(data)));

            type = bufferStream.readInt();
            checksum = new byte[bufferStream.available()];
            bufferStream.readFully(checksum);
        } catch(IOException e) {
            throw new Kerb4JException("pac.signature.malformed", null, e);
        }
    }

    public int getType() {
        return type;
    }

    public byte[] getChecksum() {
        return checksum;
    }

}