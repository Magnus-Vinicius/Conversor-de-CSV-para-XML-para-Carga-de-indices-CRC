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

public class CargaAlteracaoObito {
    public static boolean cargaAlteracaoObito(String numeroCns, String caminhoEntrada, String caminhoSaida) throws ParserConfigurationException, TransformerException {
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

                Element registroObitoAlteracao = document.createElement("REGISTROOBITOALTERACAO");

                Element indiceRegistro = document.createElement("INDICEREGISTRO");
                indiceRegistro.appendChild(document.createTextNode(indiceRegistroAtual.toString()));
                registroObitoAlteracao.appendChild(indiceRegistro);

                Element registroInvisivel = document.createElement("REGISTROINVISIVEL");
                registroInvisivel.appendChild(document.createTextNode("N"));
                registroObitoAlteracao.appendChild(registroInvisivel);

                Element codigoMotivoAlteracao = document.createElement("CODIGOMOTIVOALTERACAO");
                codigoMotivoAlteracao.appendChild(document.createTextNode("10"));
                registroObitoAlteracao.appendChild(codigoMotivoAlteracao);

                Element dataAverbacao = document.createElement("DATAAVERBACAO");
                registroObitoAlteracao.appendChild(dataAverbacao);

                Element flagDesconhecido = document.createElement("FLAGDESCONHECIDO");
                flagDesconhecido.appendChild(document.createTextNode("N"));
                registroObitoAlteracao.appendChild(flagDesconhecido);

                Element nomeFalecido = document.createElement("NOMEFALECIDO");
                nomeFalecido.appendChild(document.createTextNode(nomeFalecidoString));
                registroObitoAlteracao.appendChild(nomeFalecido);

                Element cpfFalecido = document.createElement("CPFFALECIDO");
                registroObitoAlteracao.appendChild(cpfFalecido);

                Element matricula = document.createElement("MATRICULA");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataLocalDate = LocalDate.parse(data, formatter);
                String ano = String.valueOf(dataLocalDate.getYear());
                String matriculaString = calculaMatricula(numeroCns, "01", ano, "4", livro, folha, termo);
                matricula.appendChild(document.createTextNode(matriculaString));
                registroObitoAlteracao.appendChild(matricula);

                Element dataRegistro = document.createElement("DATAREGISTRO");
                dataRegistro.appendChild(document.createTextNode(data));
                registroObitoAlteracao.appendChild(dataRegistro);

                Element nomePai = document.createElement("NOMEPAI");
                if (!nomePaiString.isBlank()) {
                    nomePai.appendChild(document.createTextNode(nomePaiString));
                }
                registroObitoAlteracao.appendChild(nomePai);

                Element cpfPai = document.createElement("CPFPAI");
                registroObitoAlteracao.appendChild(cpfPai);

                Element sexoPai = document.createElement("SEXOPAI");
                registroObitoAlteracao.appendChild(sexoPai);

                Element nomeMae = document.createElement("NOMEMAE");
                if (!nomeMaeString.isBlank()) {
                    nomeMae.appendChild(document.createTextNode(nomeMaeString));
                }
                registroObitoAlteracao.appendChild(nomeMae);

                Element cpfMae = document.createElement("CPFMAE");
                registroObitoAlteracao.appendChild(cpfMae);

                Element sexoMae = document.createElement("SEXOMAE");
                registroObitoAlteracao.appendChild(sexoMae);

                Element dataObito = document.createElement("DATAOBITO");
                registroObitoAlteracao.appendChild(dataObito);

                Element horaObito = document.createElement("HORAOBITO");
                registroObitoAlteracao.appendChild(horaObito);

                Element sexo = document.createElement("SEXO");
                registroObitoAlteracao.appendChild(sexo);

                Element corPele = document.createElement("CORPELE");
                registroObitoAlteracao.appendChild(corPele);

                Element estadoCivil = document.createElement("ESTADOCIVIL");
                registroObitoAlteracao.appendChild(estadoCivil);

                Element dataNascimentoFalecido = document.createElement("DATANASCIMENTOFALECIDO");
                registroObitoAlteracao.appendChild(dataNascimentoFalecido);

                Element idade = document.createElement("IDADE");
                registroObitoAlteracao.appendChild(idade);

                Element idadeDiasMesesAnos = document.createElement("IDADE_DIAS_MESES_ANOS");
                registroObitoAlteracao.appendChild(idadeDiasMesesAnos);

                Element eleitor = document.createElement("ELEITOR");
                registroObitoAlteracao.appendChild(eleitor);

                Element possuiBens = document.createElement("POSSUIBENS");
                registroObitoAlteracao.appendChild(possuiBens);

                Element codigoOcupacaoSDC = document.createElement("CODIGOOCUPACAOSDC");
                registroObitoAlteracao.appendChild(codigoOcupacaoSDC);

                Element paisNascimento = document.createElement("PAISNASCIMENTO");
                registroObitoAlteracao.appendChild(paisNascimento);

                Element nacionalidade = document.createElement("NACIONALIDADE");
                registroObitoAlteracao.appendChild(nacionalidade);

                Element codigoIBGEMunNaturalidade = document.createElement("CODIGOIBGEMUNNATURALIDADE");
                registroObitoAlteracao.appendChild(codigoIBGEMunNaturalidade);

                Element textoLivreMunicipioNat = document.createElement("TEXTOLIVREMUNICIPIONAT");
                registroObitoAlteracao.appendChild(textoLivreMunicipioNat);

                Element codigoIBGEMunLogradouro = document.createElement("CODIGOIBGEMUNLOGRADOURO");
                registroObitoAlteracao.appendChild(codigoIBGEMunLogradouro);

                Element domicilioEstrangeiroFalecido = document.createElement("DOMICILIOESTRANGEIROFALECIDO");
                registroObitoAlteracao.appendChild(domicilioEstrangeiroFalecido);

                Element logradouro = document.createElement("LOGRADOURO");
                registroObitoAlteracao.appendChild(logradouro);

                Element numeroLogradouro = document.createElement("NUMEROLOGRADOURO");
                registroObitoAlteracao.appendChild(numeroLogradouro);

                Element complementoLogradouro = document.createElement("COMPLEMENTOLOGRADOURO");
                registroObitoAlteracao.appendChild(complementoLogradouro);

                Element bairro = document.createElement("BAIRRO");
                registroObitoAlteracao.appendChild(bairro);

                Element tipoLocalObito = document.createElement("TIPOLOCALOBITO");
                registroObitoAlteracao.appendChild(tipoLocalObito);

                Element tipoMorte = document.createElement("TIPOMORTE");
                registroObitoAlteracao.appendChild(tipoMorte);

                Element numDeclaracaoObito = document.createElement("NUMDECLARACAOOBITO");
                registroObitoAlteracao.appendChild(numDeclaracaoObito);

                Element numDeclaracaoObitoIgnorada = document.createElement("NUMDECLARACAOOBITOIGNORADA");
                registroObitoAlteracao.appendChild(numDeclaracaoObitoIgnorada);

                Element paisObito = document.createElement("PAISOBITO");
                registroObitoAlteracao.appendChild(paisObito);

                Element codigoIBGEMunLogradouroObito = document.createElement("CODIGOIBGEMUNLOGRADOUROOBITO");
                registroObitoAlteracao.appendChild(codigoIBGEMunLogradouroObito);

                Element enderecoLocalObitoEstrangeiro = document.createElement("ENDERECOLOCALOBITOESTRANGEIRO");
                registroObitoAlteracao.appendChild(enderecoLocalObitoEstrangeiro);

                Element logradouroObito = document.createElement("LOGRADOUROOBITO");
                registroObitoAlteracao.appendChild(logradouroObito);

                Element numeroLogradouroObito = document.createElement("NUMEROLOGRADOUROOBITO");
                registroObitoAlteracao.appendChild(numeroLogradouroObito);

                Element complementoLogradouroObito = document.createElement("COMPLEMENTOLOGRADOUROOBITO");
                registroObitoAlteracao.appendChild(complementoLogradouroObito);

                Element bairroObito = document.createElement("BAIRROOBITO");
                registroObitoAlteracao.appendChild(bairroObito);

                Element causaMorteAntecedentesA = document.createElement("CAUSAMORTEANTECEDENTES_A");
                registroObitoAlteracao.appendChild(causaMorteAntecedentesA);

                Element causaMorteAntecedentesB = document.createElement("CAUSAMORTEANTECEDENTES_B");
                registroObitoAlteracao.appendChild(causaMorteAntecedentesB);

                Element causaMorteAntecedentesC = document.createElement("CAUSAMORTEANTECEDENTES_C");
                registroObitoAlteracao.appendChild(causaMorteAntecedentesC);

                Element causaMorteAntecedentesD = document.createElement("CAUSAMORTEANTECEDENTES_D");
                registroObitoAlteracao.appendChild(causaMorteAntecedentesD);

                Element causaMorteOutrasCondA = document.createElement("CAUSAMORTEOUTRASCOND_A");
                registroObitoAlteracao.appendChild(causaMorteOutrasCondA);

                Element causaMorteOutrasCondB = document.createElement("CAUSAMORTEOUTRASCOND_B");
                registroObitoAlteracao.appendChild(causaMorteOutrasCondB);

                Element lugarFalecimento = document.createElement("LUGARFALECIMENTO");
                registroObitoAlteracao.appendChild(lugarFalecimento);

                Element lugarSepultamentoCemiterio = document.createElement("LUGARSEPULTAMENTOCEMITERIO");
                registroObitoAlteracao.appendChild(lugarSepultamentoCemiterio);

                Element nomeAtestantePrimario = document.createElement("NOMEATESTANTEPRIMARIO");
                registroObitoAlteracao.appendChild(nomeAtestantePrimario);

                Element crmAtestantePrimario = document.createElement("CRMATESTANTEPRIMARIO");
                registroObitoAlteracao.appendChild(crmAtestantePrimario);

                Element nomeAtestanteSecundario = document.createElement("NOMEATESTANTESECUNDARIO");
                registroObitoAlteracao.appendChild(nomeAtestanteSecundario);

                Element crmAtestanteSecundario = document.createElement("CRMATESTANTESECUNDARIO");
                registroObitoAlteracao.appendChild(crmAtestanteSecundario);

                Element nomeDeclarante = document.createElement("NOMEDECLARANTE");
                registroObitoAlteracao.appendChild(nomeDeclarante);

                Element cpfDeclarante = document.createElement("CPFDECLARANTE");
                registroObitoAlteracao.appendChild(cpfDeclarante);

                Element orgaoEmissorExterior = document.createElement("ORGAOEMISSOREXTERIOR");
                registroObitoAlteracao.appendChild(orgaoEmissorExterior);

                Element informacoesConsulado = document.createElement("INFORMACOESCONSULADO");
                registroObitoAlteracao.appendChild(informacoesConsulado);

                Element observaocoes = document.createElement("OBSERVACOES");
                registroObitoAlteracao.appendChild(observaocoes);

                movimentoObitoTn.appendChild(registroObitoAlteracao);

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
