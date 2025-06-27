package conversorcsvcargaxml;

import static conversorcsvcargaxml.CalculoMatricula.calculaMatricula;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CargaInclusaoCasamento {

    public static boolean cargaInclusaoCasamento(String numeroCns, String caminhoEntrada, String caminhoSaida) throws ParserConfigurationException, TransformerException {
        String linha;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(caminhoEntrada), "UTF-8"))) {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element cargaRegistros = document.createElement("CARGAREGISTROS");
            document.appendChild(cargaRegistros);

            Element versao = document.createElement("VERSAO");
            versao.appendChild(document.createTextNode("2.7"));
            cargaRegistros.appendChild(versao);

            Element acao = document.createElement("ACAO");
            acao.appendChild(document.createTextNode("CARGA"));
            cargaRegistros.appendChild(acao);

            Element cns = document.createElement("CNS");
            cns.appendChild(document.createTextNode(numeroCns));
            cargaRegistros.appendChild(cns);

            Element movimentoNascimentoTn = document.createElement("MOVIMENTOCASAMENTOTC");
            cargaRegistros.appendChild(movimentoNascimentoTn);

            Integer indiceRegistroAtual = 1;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("\uFEFF")) {
                    linha = linha.substring(1);
                }
                if (indiceRegistroAtual > 2500) {
                    System.out.println("Quantidade máxima de registros excedida!\nSomente serão inseridos os primeiros 2500 registros do arquivo de entrada.");
                    break;
                }
                String[] campos = linha.split(";");
                System.out.println("Processando registro Livro " + campos[0] + ", Fls. " + campos[1] + ", Termo " + campos[2]);
                String livro = campos[0];
                String folha = campos[1];
                String termo = campos[2];
                String data = campos[3];
                String nomeConjuge1String = campos[4];
                String novoNomeConjuge1String = campos[5];
                String nomeConjuge2String = campos[6];
                String novoNomeConjuge2String = campos[7];

                Element registroCasamentoInclusao = document.createElement("REGISTROCASAMENTOINCLUSAO");

                Element indiceRegistro = document.createElement("INDICEREGISTRO");
                indiceRegistro.appendChild(document.createTextNode(indiceRegistroAtual.toString()));
                registroCasamentoInclusao.appendChild(indiceRegistro);

                Element nomeConjuge1 = document.createElement("NOMECONJUGE1");
                nomeConjuge1.appendChild(document.createTextNode(nomeConjuge1String));
                registroCasamentoInclusao.appendChild(nomeConjuge1);

                Element novoNomeConjuge1 = document.createElement("NOVONOMECONJUGE1");
                if(!novoNomeConjuge1String.isBlank()) {
                    novoNomeConjuge1.appendChild(document.createTextNode(novoNomeConjuge1String));
                }
                registroCasamentoInclusao.appendChild(novoNomeConjuge1);

                Element cpfConjuge1 = document.createElement("CPFCONJUGE1");
                registroCasamentoInclusao.appendChild(cpfConjuge1);

                Element sexoConjuge1 = document.createElement("SEXOCONJUGE1");
                sexoConjuge1.appendChild(document.createTextNode("I"));
                registroCasamentoInclusao.appendChild(sexoConjuge1);

                Element dataNascimentoConjuge1 = document.createElement("DATANASCIMENTOCONJUGE1");
                registroCasamentoInclusao.appendChild(dataNascimentoConjuge1);

                Element nomePaiConjuge1 = document.createElement("NOMEPAICONJUGE1");
                registroCasamentoInclusao.appendChild(nomePaiConjuge1);

                Element sexoPaiConjuge1 = document.createElement("SEXOPAICONJUGE1");
                registroCasamentoInclusao.appendChild(sexoPaiConjuge1);

                Element nomeMaeConjuge1 = document.createElement("NOMEMAECONJUGE1");
                registroCasamentoInclusao.appendChild(nomeMaeConjuge1);

                Element sexoMaeConjuge1 = document.createElement("SEXOMAECONJUGE1");
                registroCasamentoInclusao.appendChild(sexoMaeConjuge1);

                Element codigoOcupacaoSdcConjuge1 = document.createElement("CODIGOOCUPACAOSDCCONJUGE1");
                registroCasamentoInclusao.appendChild(codigoOcupacaoSdcConjuge1);

                Element paisNascimentoConjuge1 = document.createElement("PAISNASCIMENTOCONJUGE1");
                registroCasamentoInclusao.appendChild(paisNascimentoConjuge1);

                Element nacionalidadeConjuge1 = document.createElement("NACIONALIDADECONJUGE1");
                registroCasamentoInclusao.appendChild(nacionalidadeConjuge1);

                Element codigoIbgeMunNatConjuge1 = document.createElement("CODIGOIBGEMUNNATCONJUGE1");
                registroCasamentoInclusao.appendChild(codigoIbgeMunNatConjuge1);

                Element textoLivreMunNatConjuge1 = document.createElement("TEXTOLIVREMUNNATCONJUGE1");
                registroCasamentoInclusao.appendChild(textoLivreMunNatConjuge1);

                Element codigoIbgeMunLogradouro1 = document.createElement("CODIGOIBGEMUNLOGRADOURO1");
                registroCasamentoInclusao.appendChild(codigoIbgeMunLogradouro1);

                Element domicilioEstrangeiro1 = document.createElement("DOMICILIOESTRANGEIRO1");
                registroCasamentoInclusao.appendChild(domicilioEstrangeiro1);

                Element nomeConjuge2 = document.createElement("NOMECONJUGE2");
                nomeConjuge2.appendChild(document.createTextNode(nomeConjuge2String));
                registroCasamentoInclusao.appendChild(nomeConjuge2);

                Element novoNomeConjuge2 = document.createElement("NOVONOMECONJUGE2");
                if (!novoNomeConjuge2String.isBlank()) {
                    novoNomeConjuge2.appendChild(document.createTextNode(novoNomeConjuge2String));
                }
                registroCasamentoInclusao.appendChild(novoNomeConjuge2);

                Element cpfConjuge2 = document.createElement("CPFCONJUGE2");
                registroCasamentoInclusao.appendChild(cpfConjuge2);

                Element sexoConjuge2 = document.createElement("SEXOCONJUGE2");
                sexoConjuge2.appendChild(document.createTextNode("I"));
                registroCasamentoInclusao.appendChild(sexoConjuge2);

                Element dataNascimentoConjuge2 = document.createElement("DATANASCIMENTOCONJUGE2");
                registroCasamentoInclusao.appendChild(dataNascimentoConjuge2);

                Element nomePaiConjuge2 = document.createElement("NOMEPAICONJUGE2");
                registroCasamentoInclusao.appendChild(nomePaiConjuge2);

                Element sexoPaiConjuge2 = document.createElement("SEXOPAICONJUGE2");
                registroCasamentoInclusao.appendChild(sexoPaiConjuge2);

                Element nomeMaeConjuge2 = document.createElement("NOMEMAECONJUGE2");
                registroCasamentoInclusao.appendChild(nomeMaeConjuge2);

                Element sexoMaeConjuge2 = document.createElement("SEXOMAECONJUGE2");
                registroCasamentoInclusao.appendChild(sexoMaeConjuge2);

                Element codigoOcupacaoSdcConjuge2 = document.createElement("CODIGOOCUPACAOSDCCONJUGE2");
                registroCasamentoInclusao.appendChild(codigoOcupacaoSdcConjuge2);

                Element paisNascimentoConjuge2 = document.createElement("PAISNASCIMENTOCONJUGE2");
                registroCasamentoInclusao.appendChild(paisNascimentoConjuge2);

                Element nacionalidadeConjuge2 = document.createElement("NACIONALIDADECONJUGE2");
                registroCasamentoInclusao.appendChild(nacionalidadeConjuge2);

                Element codigoIbgeMunNatConjuge2 = document.createElement("CODIGOIBGEMUNNATCONJUGE2");
                registroCasamentoInclusao.appendChild(codigoIbgeMunNatConjuge2);

                Element textoLivreMunNatConjuge2 = document.createElement("TEXTOLIVREMUNNATCONJUGE2");
                registroCasamentoInclusao.appendChild(textoLivreMunNatConjuge2);

                Element codigoIbgeMunLogradouro2 = document.createElement("CODIGOIBGEMUNLOGRADOURO2");
                registroCasamentoInclusao.appendChild(codigoIbgeMunLogradouro2);

                Element domicilioEstrangeiro2 = document.createElement("DOMICILIOESTRANGEIRO2");
                registroCasamentoInclusao.appendChild(domicilioEstrangeiro2);

                Element matricula = document.createElement("MATRICULA");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataLocalDate = LocalDate.parse(data, formatter);
                String ano = String.valueOf(dataLocalDate.getYear());
                String matriculaString = calculaMatricula(numeroCns, "01", ano, "2", livro, folha, termo);
                matricula.appendChild(document.createTextNode(matriculaString));
                registroCasamentoInclusao.appendChild(matricula);

                Element dataRegistro = document.createElement("DATAREGISTRO");
                dataRegistro.appendChild(document.createTextNode(data));
                registroCasamentoInclusao.appendChild(dataRegistro);

                Element dataNascimento = document.createElement("DATACASAMENTO");
                registroCasamentoInclusao.appendChild(dataNascimento);

                Element regimeCasamento = document.createElement("REGIMECASAMENTO");
                regimeCasamento.appendChild(document.createTextNode("IGNORADO"));
                registroCasamentoInclusao.appendChild(regimeCasamento);

                Element orgaoEmissorExterior = document.createElement("ORGAOEMISSOREXTERIOR");
                registroCasamentoInclusao.appendChild(orgaoEmissorExterior);

                Element informacoesConsulado = document.createElement("INFORMACOESCONSULADO");
                registroCasamentoInclusao.appendChild(informacoesConsulado);

                Element observaocoes = document.createElement("OBSERVACOES");
                registroCasamentoInclusao.appendChild(observaocoes);

                movimentoNascimentoTn.appendChild(registroCasamentoInclusao);

                indiceRegistroAtual++;
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            // Configurações de formatação (indentação, encoding, etc.)
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource domSource = new DOMSource(document);
            StreamResult resultado = new StreamResult(new FileOutputStream(new File(caminhoSaida)));
            transformer.transform(domSource, resultado);

            System.out.println("Quantidade de registros processados: " + (indiceRegistroAtual - 1));
            System.out.println("Arquivo XML gerado em: " + caminhoSaida);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
