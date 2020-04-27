package ConvertAFND;

import ConvertAFND.EstadoFinal;
import ConvertAFND.EstadoInicial;
import ConvertAFND.Automato;
import ConvertAFND.Estados;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CarregarXml {

    private String criar_alfabeto(String texto, String letra) {
        
     
        String arraytexto[] = texto.split(",");
        String retorno;

        for (int i = 0; i < arraytexto.length; i++) {
            if (arraytexto[i].equals(letra)) {
                retorno = texto;
                return retorno;
            }
        }
        if (texto == "") {
            return (letra);
        } else {
            retorno = texto + "," + letra;
            return (retorno);
        }
    }

    public Automato readXml(String file) {
        Automato _automaton = new Automato();

        List<Transicao> transition = new ArrayList();
        List<Estados> estado = new ArrayList();
        List<EstadoFinal> estadofinal = new ArrayList();
        EstadoInicial estadoinicial = new EstadoInicial();
        String AlfabetoArray = "";

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            try {
                //Caminho do arquivo XML do JFLAP
                Document doc = builder.parse(file);
                NodeList listaEstados = doc.getElementsByTagName("state");
                int tamanhoEstados = listaEstados.getLength();
                for (int i = 0; i < tamanhoEstados; i++) {
                    Estados _estado = new Estados();
                    org.w3c.dom.Node noEstado = listaEstados.item(i);
                    if (noEstado.getNodeType() == Node.ELEMENT_NODE) {
                        Element elementoEstado = (Element) noEstado;
                        String id = elementoEstado.getAttribute("id");
                        String name = elementoEstado.getAttribute("name");
                        _estado.setId(Integer.parseInt(id));
                        _estado.setName(name);
                        estado.add(_estado);
                        NodeList listaDeFilhosEstado = elementoEstado.getChildNodes();
                        int tamanhoListaFilhosEstado = listaDeFilhosEstado.getLength();
                        for (int j = 0; j < tamanhoListaFilhosEstado; j++) {
                            org.w3c.dom.Node noFilhoEstado = listaDeFilhosEstado.item(j);
                            EstadoFinal e_final = new EstadoFinal();
                            if (noFilhoEstado.getNodeType() == Node.ELEMENT_NODE) {
                                Element elementoFilho = (Element) noFilhoEstado;
                                switch (elementoFilho.getTagName()) {
                                    case "initial":
                                        estadoinicial.setId(Integer.parseInt(id));
                                        estadoinicial.setName(name);
                                        break;
                                    case "final":
                                        e_final.setId(Integer.parseInt(id));
                                        e_final.setName(name);
                                        estadofinal.add(e_final);
                                        break;
                                }
                            }
                        }
                    }
                }

                String from = null;
                String to = null;
                String read = null;
                NodeList listaTrasicao = doc.getElementsByTagName("transition");
                int tamanhoTransicao = listaTrasicao.getLength();
                for (int i = 0; i < tamanhoTransicao; i++) {
                    Transicao _transition = new Transicao();
                    org.w3c.dom.Node noTransicao = listaTrasicao.item(i);
                    if (noTransicao.getNodeType() == Node.ELEMENT_NODE) {
                        Element elementoTransicao = (Element) noTransicao;
                        NodeList listaDeFilhosTransicao = elementoTransicao.getChildNodes();
                        int tamanhoListaFilhosTransicao = listaDeFilhosTransicao.getLength();
                        for (int j = 0; j < tamanhoListaFilhosTransicao; j++) {
                            org.w3c.dom.Node noFilhoTransicao = listaDeFilhosTransicao.item(j);
                            if (noFilhoTransicao.getNodeType() == Node.ELEMENT_NODE) {
                                Element elementoFilho = (Element) noFilhoTransicao;
                                switch (elementoFilho.getTagName()) {
                                    case "from":
                                        from = elementoFilho.getTextContent();
                                        break;
                                    case "to":
                                        to = elementoFilho.getTextContent();
                                        break;
                                    case "read":
                                        read = elementoFilho.getTextContent();
                                        AlfabetoArray = criar_alfabeto(AlfabetoArray, read);
                                        break;
                                }
                            }
                        }
                    }
                    _transition.setFrom(Integer.parseInt(from));
                    _transition.setRead(read);
                    _transition.setTo(Integer.parseInt(to));
                    transition.add(_transition);
                }
                _automaton.setEstado(estado);
                _automaton.setTransition(transition);
                _automaton.setEstadofinal(estadofinal);
                _automaton.setEstadoincial(estadoinicial);
                _automaton.setAlfabeto(AlfabetoArray);
            } catch (SAXException | IOException ex) {
                Logger.getLogger(CarregarXml.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CarregarXml.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _automaton;
    }
}
