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

public class CargaIncusaoObito {
    
    public static boolean cargaInclusaoObito(String numeroCns, String caminhoEntrada, String caminhoSaida) throws ParserConfigurationException, TransformerException {
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

            Element movimentoObitoTn = document.createElement("MOVIMENTOOBITOTO");
            cargaRegistros.appendChild(movimentoObitoTn);

            Integer indiceRegistroAtual = 1;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("\uFEFF")) {
                    linha = linha.substring(1);
                }
                if(indiceRegistroAtual > 2500) {
                    System.out.println("Quantidade máxima de registros excedida!\nSomente serão inseridos os primeiros 2500 registros do arquivo de entrada.");
                    break;
                }
                String[] campos = linha.split(";");
                System.out.println("Processando registro Livro " + campos[0] + ", Fls. " + campos[1] + ", Termo " + campos[2]);
                String livro = campos[0];
                String folha = campos[1];
                String termo = campos[2];
                String data = campos[3];
                String nomeFalecidoString = campos[4];
                String nomePaiString = campos[5];
                String nomeMaeString = campos[6];

                Element registroObitoInclusao = document.createElement("REGISTROOBITOINCLUSAO");

                Element indiceRegistro = document.createElement("INDICEREGISTRO");
                indiceRegistro.appendChild(document.createTextNode(indiceRegistroAtual.toString()));
                registroObitoInclusao.appendChild(indiceRegistro);

                Element flagDesconhecido = document.createElement("FLAGDESCONHECIDO");
                flagDesconhecido.appendChild(document.createTextNode("N"));
                registroObitoInclusao.appendChild(flagDesconhecido);

                Element nomeFalecido = document.createElement("NOMEFALECIDO");
                nomeFalecido.appendChild(document.createTextNode(nomeFalecidoString));
                registroObitoInclusao.appendChild(nomeFalecido);

                Element cpfFalecido = document.createElement("CPFFALECIDO");
                registroObitoInclusao.appendChild(cpfFalecido);

                Element matricula = document.createElement("MATRICULA");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataLocalDate = LocalDate.parse(data, formatter);
                String ano = String.valueOf(dataLocalDate.getYear());
                String matriculaString = calculaMatricula(numeroCns, "01", ano, "4", livro, folha, termo);
                matricula.appendChild(document.createTextNode(matriculaString));
                registroObitoInclusao.appendChild(matricula);

                Element dataRegistro = document.createElement("DATAREGISTRO");
                dataRegistro.appendChild(document.createTextNode(data));
                registroObitoInclusao.appendChild(dataRegistro);

                Element nomePai = document.createElement("NOMEPAI");
                if (!nomePaiString.isBlank()) {
                    nomePai.appendChild(document.createTextNode(nomePaiString));
                }
                registroObitoInclusao.appendChild(nomePai);

                Element cpfPai = document.createElement("CPFPAI");
                registroObitoInclusao.appendChild(cpfPai);

                Element sexoPai = document.createElement("SEXOPAI");
                registroObitoInclusao.appendChild(sexoPai);

                Element nomeMae = document.createElement("NOMEMAE");
                if (!nomeMaeString.isBlank()) {
                    nomeMae.appendChild(document.createTextNode(nomeMaeString));
                }
                registroObitoInclusao.appendChild(nomeMae);

                Element cpfMae = document.createElement("CPFMAE");
                registroObitoInclusao.appendChild(cpfMae);

                Element sexoMae = document.createElement("SEXOMAE");
                registroObitoInclusao.appendChild(sexoMae);

                Element dataObito = document.createElement("DATAOBITO");
                registroObitoInclusao.appendChild(dataObito);

                Element horaObito = document.createElement("HORAOBITO");
                registroObitoInclusao.appendChild(horaObito);

                Element sexo = document.createElement("SEXO");
                registroObitoInclusao.appendChild(sexo);

                Element corPele = document.createElement("CORPELE");
                registroObitoInclusao.appendChild(corPele);

                Element estadoCivil = document.createElement("ESTADOCIVIL");
                registroObitoInclusao.appendChild(estadoCivil);

                Element dataNascimentoFalecido = document.createElement("DATANASCIMENTOFALECIDO");
                registroObitoInclusao.appendChild(dataNascimentoFalecido);

                Element idade = document.createElement("IDADE");
                registroObitoInclusao.appendChild(idade);

                Element idadeDiasMesesAnos = document.createElement("IDADE_DIAS_MESES_ANOS");
                registroObitoInclusao.appendChild(idadeDiasMesesAnos);

                Element eleitor = document.createElement("ELEITOR");
                registroObitoInclusao.appendChild(eleitor);

                Element possuiBens = document.createElement("POSSUIBENS");
                registroObitoInclusao.appendChild(possuiBens);

                Element codigoOcupacaoSDC = document.createElement("CODIGOOCUPACAOSDC");
                registroObitoInclusao.appendChild(codigoOcupacaoSDC);

                Element paisNascimento = document.createElement("PAISNASCIMENTO");
                registroObitoInclusao.appendChild(paisNascimento);

                Element nacionalidade = document.createElement("NACIONALIDADE");
                registroObitoInclusao.appendChild(nacionalidade);

                Element codigoIBGEMunNaturalidade = document.createElement("CODIGOIBGEMUNNATURALIDADE");
                registroObitoInclusao.appendChild(codigoIBGEMunNaturalidade);

                Element textoLivreMunicipioNat = document.createElement("TEXTOLIVREMUNICIPIONAT");
                registroObitoInclusao.appendChild(textoLivreMunicipioNat);

                Element codigoIBGEMunLogradouro = document.createElement("CODIGOIBGEMUNLOGRADOURO");
                registroObitoInclusao.appendChild(codigoIBGEMunLogradouro);

                Element domicilioEstrangeiroFalecido = document.createElement("DOMICILIOESTRANGEIROFALECIDO");
                registroObitoInclusao.appendChild(domicilioEstrangeiroFalecido);

                Element logradouro = document.createElement("LOGRADOURO");
                registroObitoInclusao.appendChild(logradouro);

                Element numeroLogradouro = document.createElement("NUMEROLOGRADOURO");
                registroObitoInclusao.appendChild(numeroLogradouro);

                Element complementoLogradouro = document.createElement("COMPLEMENTOLOGRADOURO");
                registroObitoInclusao.appendChild(complementoLogradouro);

                Element bairro = document.createElement("BAIRRO");
                registroObitoInclusao.appendChild(bairro);

                Element tipoLocalObito = document.createElement("TIPOLOCALOBITO");
                registroObitoInclusao.appendChild(tipoLocalObito);

                Element tipoMorte = document.createElement("TIPOMORTE");
                registroObitoInclusao.appendChild(tipoMorte);

                Element numDeclaracaoObito = document.createElement("NUMDECLARACAOOBITO");
                registroObitoInclusao.appendChild(numDeclaracaoObito);

                Element numDeclaracaoObitoIgnorada = document.createElement("NUMDECLARACAOOBITOIGNORADA");
                registroObitoInclusao.appendChild(numDeclaracaoObitoIgnorada);

                Element paisObito = document.createElement("PAISOBITO");
                registroObitoInclusao.appendChild(paisObito);

                Element codigoIBGEMunLogradouroObito = document.createElement("CODIGOIBGEMUNLOGRADOUROOBITO");
                registroObitoInclusao.appendChild(codigoIBGEMunLogradouroObito);

                Element enderecoLocalObitoEstrangeiro = document.createElement("ENDERECOLOCALOBITOESTRANGEIRO");
                registroObitoInclusao.appendChild(enderecoLocalObitoEstrangeiro);

                Element logradouroObito = document.createElement("LOGRADOUROOBITO");
                registroObitoInclusao.appendChild(logradouroObito);

                Element numeroLogradouroObito = document.createElement("NUMEROLOGRADOUROOBITO");
                registroObitoInclusao.appendChild(numeroLogradouroObito);

                Element complementoLogradouroObito = document.createElement("COMPLEMENTOLOGRADOUROOBITO");
                registroObitoInclusao.appendChild(complementoLogradouroObito);

                Element bairroObito = document.createElement("BAIRROOBITO");
                registroObitoInclusao.appendChild(bairroObito);

                Element causaMorteAntecedentesA = document.createElement("CAUSAMORTEANTECEDENTES_A");
                registroObitoInclusao.appendChild(causaMorteAntecedentesA);

                Element causaMorteAntecedentesB = document.createElement("CAUSAMORTEANTECEDENTES_B");
                registroObitoInclusao.appendChild(causaMorteAntecedentesB);

                Element causaMorteAntecedentesC = document.createElement("CAUSAMORTEANTECEDENTES_C");
                registroObitoInclusao.appendChild(causaMorteAntecedentesC);

                Element causaMorteAntecedentesD = document.createElement("CAUSAMORTEANTECEDENTES_D");
                registroObitoInclusao.appendChild(causaMorteAntecedentesD);

                Element causaMorteOutrasCondA = document.createElement("CAUSAMORTEOUTRASCOND_A");
                registroObitoInclusao.appendChild(causaMorteOutrasCondA);

                Element causaMorteOutrasCondB = document.createElement("CAUSAMORTEOUTRASCOND_B");
                registroObitoInclusao.appendChild(causaMorteOutrasCondB);

                Element lugarFalecimento = document.createElement("LUGARFALECIMENTO");
                registroObitoInclusao.appendChild(lugarFalecimento);

                Element lugarSepultamentoCemiterio = document.createElement("LUGARSEPULTAMENTOCEMITERIO");
                registroObitoInclusao.appendChild(lugarSepultamentoCemiterio);

                Element nomeAtestantePrimario = document.createElement("NOMEATESTANTEPRIMARIO");
                registroObitoInclusao.appendChild(nomeAtestantePrimario);

                Element crmAtestantePrimario = document.createElement("CRMATESTANTEPRIMARIO");
                registroObitoInclusao.appendChild(crmAtestantePrimario);

                Element nomeAtestanteSecundario = document.createElement("NOMEATESTANTESECUNDARIO");
                registroObitoInclusao.appendChild(nomeAtestanteSecundario);

                Element crmAtestanteSecundario = document.createElement("CRMATESTANTESECUNDARIO");
                registroObitoInclusao.appendChild(crmAtestanteSecundario);

                Element nomeDeclarante = document.createElement("NOMEDECLARANTE");
                registroObitoInclusao.appendChild(nomeDeclarante);

                Element cpfDeclarante = document.createElement("CPFDECLARANTE");
                registroObitoInclusao.appendChild(cpfDeclarante);

                Element orgaoEmissorExterior = document.createElement("ORGAOEMISSOREXTERIOR");
                registroObitoInclusao.appendChild(orgaoEmissorExterior);

                Element informacoesConsulado = document.createElement("INFORMACOESCONSULADO");
                registroObitoInclusao.appendChild(informacoesConsulado);

                Element observaocoes = document.createElement("OBSERVACOES");
                registroObitoInclusao.appendChild(observaocoes);

                movimentoObitoTn.appendChild(registroObitoInclusao);

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
