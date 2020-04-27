package ConvertAFND;

import ConvertAFND.GerarXML;
import ConvertAFND.CarregarXml;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import ConvertAFND.Automato;
import ConvertAFND.Estados;
import ConvertAFND.EstadoFinal;
import ConvertAFND.EstadoInicial;

public class Inicial extends javax.swing.JFrame {

    private final CarregarXml _readXml = new CarregarXml();
    
    private Automato automato_afn = new Automato();
    private final Automato auto_afd = new Automato();
    
    private final List<Estados> estados_afd = new ArrayList<>();
    private final List<Transicao> transicoes_afd = new ArrayList<>();
    private final List<EstadoFinal> estadosFinaisAFD = new ArrayList<>();
    private final List<EstadoInicial> estadosInicialAFD = new ArrayList<>();
    private final String Alfabeto_afd = automato_afn.getAlfabeto();
   
    private String alfabeto[];
    private int proximaLinhaAFD;
    private int qtdLinhasAFND;
    private int qtdLinhasAFD;
    private String arqName;

    public Inicial() {
        initComponents();
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        jButtonAbrir = new javax.swing.JButton();
        jTextFieldEstados = new javax.swing.JTextField();
        jTextFieldAlfabeto = new javax.swing.JTextField();
        jTextFieldEstadoInicial = new javax.swing.JTextField();
        getEntrada = new javax.swing.JTextField();
        jTextFieldEstadosFinais = new javax.swing.JTextField();
        sequeEntrada = new javax.swing.JCheckBox();
        buttonConverter = new javax.swing.JButton();
        transicaoAFD = new javax.swing.JTable();
        transicaoAFND = new javax.swing.JTable();
        jButtonGerarAFD = new javax.swing.JButton();
        jLabelCarregaArquivoXml = new javax.swing.JLabel();
        jLabelConverteAFN = new javax.swing.JLabel();
        jLabelCriaXml = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Convert AFND em AFD ");

        jButtonAbrir.setText("Abrir");
        jButtonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1GetFile(evt);
            }
        });

        jTextFieldAlfabeto.setEditable(false);

        jTextFieldEstadoInicial.setEditable(false);

        getEntrada.setEnabled(false);

        jTextFieldEstadosFinais.setEditable(false);

        buttonConverter.setText("Converter ");
        buttonConverter.setEnabled(false);
        buttonConverter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConverterActionPerformed(evt);
            }
        });

        transicaoAFD.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"", "", "", null},
                    {"", "", "", null},
                    {"", "", "", null},
                    {"", "", "", null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "q", "\u03c3", "\u03b4(\u03c3,q)", "F"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        transicaoAFND.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"", "", ""},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null}
                },
                new String[]{
                    "q", "\u03c3", "\u03b4(\u03c3,q)"
                }
        ));

        jButtonGerarAFD.setText("Gerar AFD ");
        jButtonGerarAFD.setEnabled(false);
        jButtonGerarAFD.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton2ActionPerformed(evt);
        });

        jLabelCarregaArquivoXml.setText("1º Carrega arquivo xml contendo um AFN.");

        jLabelConverteAFN.setText("2º Converte um AFN em AFD. ");

        jLabelCriaXml.setText("3º Cria um Xml contendo um AFD.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jButtonGerarAFD)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabelCriaXml))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(buttonConverter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jButtonAbrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabelConverteAFN)
                                                                        .addComponent(jLabelCarregaArquivoXml)
                                                                )))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        ))
                                .addGap(0, 18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                )
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(44, 44, 44)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButtonAbrir)
                                                        .addComponent(jLabelCarregaArquivoXml))
                                                .addGap(32, 32, 32)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(buttonConverter)
                                                        .addComponent(jLabelConverteAFN))
                                                .addGap(32, 32, 32)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                )
                                                .addGap(32, 32, 32)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButtonGerarAFD)
                                                        .addComponent(jLabelCriaXml))))
                                .addGap(11, 11, 11))
        );

        pack();
    }

    private void jButton1GetFile(java.awt.event.ActionEvent evt) {
        try {
            String current = new java.io.File(".").getCanonicalPath(); // Retorna uma sequencia de nomes de caminhos sem os "."
            
            JFileChooser fileChooser = new JFileChooser(); // Cria um seletor de arquivos
                
            fileChooser.setCurrentDirectory(new File(current + "\\Arquivo_In"));
            int result = fileChooser.showOpenDialog(this);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                
                File selectedFile = fileChooser.getSelectedFile(); // selectedFile armazena o caminho do aquivo
               
                arqName = selectedFile.getName();
               
                automato_afn = _readXml.readXml(selectedFile.getAbsolutePath());
                preencheTabela();
            }
        } catch (IOException ex) {
            Logger.getLogger(Inicial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preencheTabela() {
        List<Estados> estados = automato_afn.getEstado();
        String Alfabeto = automato_afn.getAlfabeto();
        EstadoInicial estadoInicial = automato_afn.getEstadoincial();
        List<EstadoFinal> estadoFinal = automato_afn.getEstadofinal();
        List<Transicao> transition = automato_afn.getTransition();

        String estado_Names = estados.stream().map(Estados::getName).collect(Collectors.joining(", "));
        String estado_final = estadoFinal.stream().map(EstadoFinal::getName).collect(Collectors.joining(", "));
        String _transition = transition.stream().map(Transicao::getRead).collect(Collectors.joining(""));

        jTextFieldEstados.setText(estado_Names);
        jTextFieldAlfabeto.setText(Alfabeto);
        jTextFieldEstadoInicial.setText(estadoInicial.getName());
        jTextFieldEstadosFinais.setText(estado_final);
        buttonConverter.setEnabled(true);
        if (sequeEntrada.isSelected()) {
            getEntrada.setText("");
        } else {
            getEntrada.setText(_transition);
        }
        for (int i = 0;
                i < transition.size();
                i++) {
            String from = estados.get(transition.get(i).getFrom()).getName();
            String read = transition.get(i).getRead();
            String to = estados.get(transition.get(i).getTo()).getName();
            transicaoAFND.setValueAt(from, i, 0);
            transicaoAFND.setValueAt(read, i, 1);
            transicaoAFND.setValueAt(to, i, 2);
        }
    }

    private void buttonConverterActionPerformed(java.awt.event.ActionEvent evt) {
        int i;
        qtdLinhasAFND = 0;
        proximaLinhaAFD = 0;

        for (i = 0; i < transicaoAFD.getRowCount(); i++) {
            transicaoAFD.setValueAt(null, i, 0);
            transicaoAFD.setValueAt(null, i, 1);
            transicaoAFD.setValueAt(null, i, 2);
            transicaoAFD.setValueAt(null, i, 3);
        }
        alfabeto = jTextFieldAlfabeto.getText().split(",");

        for (i = 0; (i < transicaoAFND.getRowCount() && transicaoAFND.getValueAt(i, 0) != null); i++) {
            if (!transicaoAFND.getValueAt(i, 0).toString().isEmpty()) {
                qtdLinhasAFND++;
            } else {
                break;
            }
        }

        constroiEstados(automato_afn.getEstadoincial().getName());
        getEntrada.setEnabled(true);
        jButtonGerarAFD.setEnabled(true);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            GerarXML gera = new GerarXML();
            gera.gerar(auto_afd, arqName, true);
            JOptionPane.showMessageDialog(null, "Arquivo AFD_" + arqName + " foi gerado com sucesso!");
        } catch (IOException ex) {
            Logger.getLogger(Inicial.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buttonExecutarActionPerformed(java.awt.event.ActionEvent evt) {
        GerarXML gera = new GerarXML();
        try {
            gera.gerar(auto_afd, arqName, true);

        } catch (IOException ex) {
            Logger.getLogger(Inicial.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        int i, j;

        qtdLinhasAFD = transicoes_afd.size();

        String p = auto_afd.getEstadoincial().getName();    // Seleciona o estado inicial
        for (i = 0; i < getEntrada.getText().length(); i++) {
            // Encontra o próximo estado
            p = proximoEstado(p, getEntrada.getText().substring(i, i + 1));
        }
    }

    private void jTextFieldEstadosActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void constroiEstados(String estado) {
        int i, j, k;
        String novoEstado, estadoFinal;
        String estados[];
        List<Transicao> transicoesAFND = automato_afn.getTransition();

        for (j = 0; j < alfabeto.length; j++) {
            novoEstado = "";
            estados = estado.split(",");

            for (k = 0; k < estados.length; k++) {
                // Percorre a tabela de transições do AFND
                for (i = 0; i < transicoesAFND.size(); i++) {
                    // Encontra a linha onde o estado de origem é o estado que está sendo testado
                    if (localizarTransicao(transicoesAFND.get(i).getFrom(), automato_afn).equals(estados[k]) && transicoesAFND.get(i).getRead().equals(alfabeto[j])) {
                        // Somente inclui em novoEstado se novoEstado não contém o estado
                        if (!contemEstado(novoEstado, localizarTransicao(transicoesAFND.get(i).getTo(), automato_afn))) {
                            if (!"".equals(novoEstado)) {
                                novoEstado += ",";
                            }
                            novoEstado += localizarTransicao(transicoesAFND.get(i).getTo(), automato_afn);
                        }
                    }
                }
            }

            // Se não encontrou novoEstado então vai para a próxima iteração
            if (novoEstado == "") {
                continue;
            }

            boolean verifica = false;
            // Verifica se novoEstado já foi incluído na tabela AFD
            for (i = 0; i < transicoes_afd.size(); i++) {
                //if (tabelaTransicaoAFD.getValueAt(i,0).toString().equals(novoEstado))
                if (transicoes_afd.get(i).getFrom() == retornaIdEstado(estado)
                        && transicoes_afd.get(i).getRead().equals(alfabeto[j])
                        && transicoes_afd.get(i).getTo() == retornaIdEstado(novoEstado)) {
                    verifica = true;
                    break;
                }
            }
            // Se novoEstado ainda não foi incluído no AFD, então inclui e constrói novos estados
            if (!verifica) {
                Estados newEstado = new Estados();
                Transicao newTransicao = new Transicao();
                EstadoFinal newEstadoFinal = new EstadoFinal();
                newEstado.setId(estados_afd.size());
                newEstado.setName(estado);
                newEstado = adicionarEstado(newEstado);

                String aux = newEstado.getName().replaceAll("q", "");

                transicaoAFD.setValueAt("q" + newEstado.getId() + " [" + aux + "]", proximaLinhaAFD, 0);
                transicaoAFD.setValueAt(alfabeto[j], proximaLinhaAFD, 1);
                newEstado = new Estados();
                newEstado.setId(estados_afd.size());
                newEstado.setName(novoEstado);
                newEstado = adicionarEstado(newEstado);

                aux = newEstado.getName().replaceAll("q", "");

                transicaoAFD.setValueAt("q" + newEstado.getId() + " [" + aux + "]", proximaLinhaAFD, 2);
                newTransicao.setFrom(retornaIdEstado(estado));
                newTransicao.setTo(retornaIdEstado(novoEstado));
                newTransicao.setRead(alfabeto[j]);
                transicoes_afd.add(newTransicao);
                estadoFinal = estadoFinalAFND(novoEstado);

                transicaoAFD.setValueAt(estadoFinal, proximaLinhaAFD, 3);
                if (estadoFinal.equals("*")) {
                    if (!verficaEstadoFinalExiste(novoEstado)) {
                        newEstadoFinal.setId(retornaIdEstado(novoEstado));
                        newEstadoFinal.setName(novoEstado);
                        estadosFinaisAFD.add(newEstadoFinal);
                    }
                }

                proximaLinhaAFD++;
                constroiEstados(novoEstado);
            }
        }
        auto_afd.setAlfabeto(Alfabeto_afd);
        auto_afd.setEstado(estados_afd);
        auto_afd.setEstadofinal(estadosFinaisAFD);
        auto_afd.setTransition(transicoes_afd);
        auto_afd.setEstadoincial(automato_afn.getEstadoincial());

    }

    

    private String localizarTransicao(int id, Automato auto) {
        List<Estados> e = auto.getEstado();
        for (int i = 0; i < e.size(); i++) {
            if (e.get(i).getId() == id) {
                return e.get(i).getName();
            }
        }
        return "";
    }

    private boolean verficaEstadoFinalExiste(String nome) {
        boolean verifica = false;
        for (int i = 0; i < estadosFinaisAFD.size(); i++) {
            if ((nome.equals(estadosFinaisAFD.get(i).getName()))) {
                verifica = true;
            }
        }
        return verifica;
    }

    private int retornaIdEstado(String nome) {
        int id = -1;
        for (int i = 0; i < estados_afd.size(); i++) {
            if ((nome.equals(estados_afd.get(i).getName()))) {
                id = estados_afd.get(i).getId();
            }
        }
        return id;
    }

    private Estados adicionarEstado(Estados e) {
        boolean verifica = false;
        for (int i = 0; i < estados_afd.size(); i++) {
            if ((e.getName().equals(estados_afd.get(i).getName()))) {
                e = estados_afd.get(i);
                return e;
            }
        }
        if (!verifica) {
            estados_afd.add(e);
        }
        return e;
    }

    private boolean contemEstado(String estado1, String estado2) {
        int i;
        String estados[] = estado1.split(",");
        for (i = 0; i < estados.length; i++) {
            if (estados[i].equals(estado2)) {
                break;
            }
        }
        if (i < estados.length) {
            return true;
        } else {
            return false;
        }
    }

    private String estadoFinalAFND(String s) {
        int i, j;
        String estadosFinais[] = jTextFieldEstadosFinais.getText().split(",");
        String estadosAux[] = s.split(",");

        for (i = 0; i < estadosFinais.length; i++) {
            for (j = 0; j < estadosAux.length; j++) {
                if (estadosFinais[i].equals(estadosAux[j])) {
                    return "*";
                }
            }
        }
        return "";
    }

    private String proximoEstado(String p1, String p2) {
        int i;
        String s = null;

        for (i = 0; i < transicoes_afd.size(); i++) {
            if (transicoes_afd.get(i).getFrom() == (retornaIdEstado(p1))
                    && transicoes_afd.get(i).getRead().equals(p2)) {
                s = localizarTransicao(transicoes_afd.get(i).getTo(), auto_afd);
                break;
            }
        }
        return s;
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new Inicial().setVisible(true);
        });
    }

    private javax.swing.JButton buttonConverter;
    private javax.swing.JButton jButtonAbrir;
    private javax.swing.JButton jButtonGerarAFD;
    private javax.swing.JCheckBox sequeEntrada;
    private javax.swing.JLabel jLabelCarregaArquivoXml;
    private javax.swing.JLabel jLabelConverteAFN;
    private javax.swing.JLabel jLabelCriaXml;
    private javax.swing.JTextField jTextFieldAlfabeto;
    private javax.swing.JTextField getEntrada;
    private javax.swing.JTextField jTextFieldEstadoInicial;
    private javax.swing.JTextField jTextFieldEstados;
    private javax.swing.JTextField jTextFieldEstadosFinais;
    private javax.swing.JTable transicaoAFD;
    private javax.swing.JTable transicaoAFND;
}
