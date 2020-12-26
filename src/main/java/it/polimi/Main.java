package it.polimi;
import it.polimi.converter.Converter;

public class Main {

    public static void main(String[] args) throws Exception {
        Converter.convert(args[0], args[0].concat(".owl"));
    }
}