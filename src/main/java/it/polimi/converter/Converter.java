package it.polimi.converter;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.PrefixDocumentFormat;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.formats.TurtleDocumentFormat;
import org.semanticweb.owlapi.io.FileDocumentSource;
import org.semanticweb.owlapi.io.OWLOntologyDocumentSource;
import org.semanticweb.owlapi.model.*;

import java.io.File;

public class Converter {
    public static void convert(String inputFile, String outputFile) throws OWLOntologyCreationException, OWLOntologyStorageException {
        File input = new File(inputFile);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        RDFXMLDocumentFormat out = new RDFXMLDocumentFormat();
        OWLOntologyDocumentSource source = new FileDocumentSource(input, new TurtleDocumentFormat());
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(source);
        OWLDocumentFormat in = manager.getOntologyFormat(ontology);
        if (in instanceof PrefixDocumentFormat) {
            out.asPrefixOWLDocumentFormat().setPrefixManager(in.asPrefixOWLDocumentFormat());
        }
        File output = new File(outputFile);
        manager.saveOntology(ontology, out, IRI.create(output.toURI()));
    }
}
