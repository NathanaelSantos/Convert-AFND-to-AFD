package ConvertAFND;

import ConvertAFND.EstadoFinal;
import ConvertAFND.EstadoInicial;
import ConvertAFND.Automato;
import ConvertAFND.Estados;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class GerarXML {

    public boolean estadoFinal(int id, List<EstadoFinal> lista) {
        for (int i = 0; i < lista.size(); i++) {
            if (id == lista.get(i).getId()) {
                return true;
            }
        }
        return false;
    }

    public boolean estadoInicial(int id, EstadoInicial lista) {
        return id == lista.getId();
    }

    public void gerar(Automato AFD, String nome, boolean gravar) throws IOException {

        List<Transicao> transicoes_AFD = AFD.getTransition();
        List<Estados> estados = AFD.getEstado();
        List<EstadoFinal> EstadosFinais = AFD.getEstadofinal();
        EstadoInicial estadoIncial = AFD.getEstadoincial();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document documentXML = documentBuilder.newDocument();
            Element structure = documentXML.createElement("structure");

            documentXML.appendChild(structure);
            Element type = documentXML.createElement("type");
            type.appendChild(documentXML.createTextNode("fa"));

            structure.appendChild(type);

            Element automaton = documentXML.createElement("automaton");
            structure.appendChild(automaton);
            String id_estado = "";

            for (int i = 0; i < estados.size(); i++) {
                String auxc = "";
                Element state = documentXML.createElement("state");

                Attr id = documentXML.createAttribute("id");
                Attr name = documentXML.createAttribute("name");

                id_estado = Integer.toString(estados.get(i).getId());
                id.setValue(id_estado);
                name.setValue("q" + id_estado);

                state.setAttributeNode(id);
                state.setAttributeNode(name);

                Element eixoX = documentXML.createElement("x");
                eixoX.appendChild(documentXML.createTextNode("309.0"));
                state.appendChild(eixoX);

                Element eixoY = documentXML.createElement("y");
                eixoY.appendChild(documentXML.createTextNode("221.0"));
                state.appendChild(eixoY);

                Element label = documentXML.createElement("label");
                String aux = estados.get(i).getName().replaceAll("q", "");
                label.appendChild(documentXML.createTextNode(aux));
                state.appendChild(label);

                if (estadoFinal(estados.get(i).getId(), EstadosFinais)) {
                    Element final_estado = documentXML.createElement("final");
                    state.appendChild(final_estado);

                }
                if (estadoInicial(estados.get(i).getId(), estadoIncial)) {
                    Element inicial = documentXML.createElement("initial");
                    state.appendChild(inicial);
                }
                automaton.appendChild(state);
             
            }

            for (int i = 0; i < transicoes_AFD.size(); i++) {
                Element transition = documentXML.createElement("transition");

                Element from = documentXML.createElement("from");
                from.appendChild(documentXML.createTextNode(Integer.toString(transicoes_AFD.get(i).getFrom())));
                transition.appendChild(from);

                Element to = documentXML.createElement("to");
                to.appendChild(documentXML.createTextNode(Integer.toString(transicoes_AFD.get(i).getTo())));
                transition.appendChild(to);

                Element read = documentXML.createElement("read");
                read.appendChild(documentXML.createTextNode(transicoes_AFD.get(i).getRead()));
                transition.appendChild(read);

                automaton.appendChild(transition);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            if (gravar) {
                String current = new java.io.File(".").getCanonicalPath();

                DOMSource documentoFonte = new DOMSource(documentXML);
                //Caminha de onde será salvo o arquivo XML
                StreamResult documentoFinal = new StreamResult(new File(current + "ArquivoConvertido" + nome));
                transformer.transform(documentoFonte, documentoFinal);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GerarXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(GerarXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(GerarXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
