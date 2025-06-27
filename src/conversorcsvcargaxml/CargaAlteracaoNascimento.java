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

public class CargaAlteracaoNascimento {
    
    public static boolean cargaAlteracaoNascimento(String numeroCns, String caminhoEntrada, String caminhoSaida) throws ParserConfigurationException, TransformerException {
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

            Element movimentoNascimentoTn = document.createElement("MOVIMENTONASCIMENTOTN");
            cargaRegistros.appendChild(movimentoNascimentoTn);

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
                String nome = campos[4];
                String nomePaiString = campos[5];
                String nomeMaeString = campos[6];

                Element registroNascimentoAlteracao = document.createElement("REGISTRONASCIMENTOALTERACAO");

                Element indiceRegistro = document.createElement("INDICEREGISTRO");
                indiceRegistro.appendChild(document.createTextNode(indiceRegistroAtual.toString()));
                registroNascimentoAlteracao.appendChild(indiceRegistro);
                
                Element registroInvisivel = document.createElement("REGISTROINVISIVEL");
                registroInvisivel.appendChild(document.createTextNode("N"));
                registroNascimentoAlteracao.appendChild(registroInvisivel);
                
                Element codigoMotivoAlteracao = document.createElement("CODIGOMOTIVOALTERACAO");
                codigoMotivoAlteracao.appendChild(document.createTextNode("10"));
                registroNascimentoAlteracao.appendChild(codigoMotivoAlteracao);
                
                Element dataAverbacao = document.createElement("DATAAVERBACAO");
                registroNascimentoAlteracao.appendChild(dataAverbacao);

                Element nomeRegistrado = document.createElement("NOMEREGISTRADO");
                nomeRegistrado.appendChild(document.createTextNode(nome));
                registroNascimentoAlteracao.appendChild(nomeRegistrado);
                
                Element cpfRegistrado = document.createElement("CPFREGISTRADO");
                registroNascimentoAlteracao.appendChild(cpfRegistrado);

                Element matricula = document.createElement("MATRICULA");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataLocalDate = LocalDate.parse(data, formatter);
                String ano = String.valueOf(dataLocalDate.getYear());
                String matriculaString = calculaMatricula(numeroCns, "01", ano, "1", livro, folha, termo);
                matricula.appendChild(document.createTextNode(matriculaString));
                registroNascimentoAlteracao.appendChild(matricula);

                Element dataRegistro = document.createElement("DATAREGISTRO");
                dataRegistro.appendChild(document.createTextNode(data));
                registroNascimentoAlteracao.appendChild(dataRegistro);
                
                Element dnv = document.createElement("DNV");
                registroNascimentoAlteracao.appendChild(dnv);
                
                Element dataNascimento = document.createElement("DATANASCIMENTO");
                registroNascimentoAlteracao.appendChild(dataNascimento);
                
                Element horaNascimento = document.createElement("HORANASCIMENTO");
                registroNascimentoAlteracao.appendChild(horaNascimento);
                
                Element localNascimento = document.createElement("LOCALNASCIMENTO");
                registroNascimentoAlteracao.appendChild(localNascimento);

                Element sexo = document.createElement("SEXO");
                sexo.appendChild(document.createTextNode("I"));
                registroNascimentoAlteracao.appendChild(sexo);
                
                Element possuiGemeos = document.createElement("POSSUIGEMEOS");
                registroNascimentoAlteracao.appendChild(possuiGemeos);
                
                Element numeroGemeos = document.createElement("NUMEROGEMEOS");
                registroNascimentoAlteracao.appendChild(numeroGemeos);
                
                Element codigoIbgeMunNascimento = document.createElement("CODIGOIBGEMUNNASCIMENTO");
                registroNascimentoAlteracao.appendChild(codigoIbgeMunNascimento);
                
                Element paisNascimento = document.createElement("PAISNASCIMENTO");
                registroNascimentoAlteracao.appendChild(paisNascimento);
                
                Element nacionalidade = document.createElement("NACIONALIDADE");
                registroNascimentoAlteracao.appendChild(nacionalidade);
                
                Element textoNacionalidadeEstrangeiro = document.createElement("TEXTONACIONALIDADEESTRANGEIRO");
                registroNascimentoAlteracao.appendChild(textoNacionalidadeEstrangeiro);

                Integer indiceFiliacaoInt = 0;
                
                if (!nomePaiString.isBlank()) {
                    Element filiacaoNascimento = document.createElement("FILIACAONASCIMENTO");
                    Element indiceRegistroPai = document.createElement("INDICEREGISTRO");
                    indiceRegistroPai.appendChild(document.createTextNode(indiceRegistroAtual.toString()));
                    filiacaoNascimento.appendChild(indiceRegistroPai);
                    Element indiceFiliacao = document.createElement("INDICEFILIACAO");
                    indiceFiliacao.appendChild(document.createTextNode(indiceRegistroAtual.toString() + indiceFiliacaoInt.toString()));
                    filiacaoNascimento.appendChild(indiceFiliacao);
                    Element nomePai = document.createElement("NOME");
                    nomePai.appendChild(document.createTextNode(nomePaiString));
                    filiacaoNascimento.appendChild(nomePai);
                    
                    addTagsVazias(document, filiacaoNascimento);
                    
                    registroNascimentoAlteracao.appendChild(filiacaoNascimento);
                    indiceFiliacaoInt++;
                }

                if (!nomeMaeString.isBlank()) {
                    Element filiacaoNascimento = document.createElement("FILIACAONASCIMENTO");
                    Element indiceRegistroMae = document.createElement("INDICEREGISTRO");
                    indiceRegistroMae.appendChild(document.createTextNode(indiceRegistroAtual.toString()));
                    filiacaoNascimento.appendChild(indiceRegistroMae);
                    Element indiceFiliacao = document.createElement("INDICEFILIACAO");
                    indiceFiliacao.appendChild(document.createTextNode(indiceRegistroAtual.toString() + indiceFiliacaoInt.toString()));
                    filiacaoNascimento.appendChild(indiceFiliacao);
                    Element nomeMae = document.createElement("NOME");
                    nomeMae.appendChild(document.createTextNode(nomeMaeString));
                    filiacaoNascimento.appendChild(nomeMae);
                    
                    Element sexoFiliacao = document.createElement("SEXO");
                    filiacaoNascimento.appendChild(sexoFiliacao);
                    
                    addTagsVazias(document, filiacaoNascimento);
                    
                    registroNascimentoAlteracao.appendChild(filiacaoNascimento);
                }

                Element orgaoEmissorExterior = document.createElement("ORGAOEMISSOREXTERIOR");
                registroNascimentoAlteracao.appendChild(orgaoEmissorExterior);
                
                Element informacoesConsulado = document.createElement("INFORMACOESCONSULADO");
                registroNascimentoAlteracao.appendChild(informacoesConsulado);
                
                Element observaocoes = document.createElement("OBSERVACOES");
                registroNascimentoAlteracao.appendChild(observaocoes);
                
                movimentoNascimentoTn.appendChild(registroNascimentoAlteracao);

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

            System.out.println("Quantidade de registros processados: " + (indiceRegistroAtual-1));
            System.out.println("Arquivo XML gerado em: " + caminhoSaida);
            return true;

        } catch (IOException e) {
            return false;
        }
    }
    
    private static void addTagsVazias(Document document, Element filiacaoNascimento) {
         Element cpfFiliacao = document.createElement("CPF");
         filiacaoNascimento.appendChild(cpfFiliacao);
         Element dataNascimento = document.createElement("DATANASCIMENTO");
         filiacaoNascimento.appendChild(dataNascimento);
         Element idade = document.createElement("IDADE");
         filiacaoNascimento.appendChild(idade);
         Element idadeDiasMesesAnos  = document.createElement("IDADE_DIAS_MESES_ANOS");
         filiacaoNascimento.appendChild(idadeDiasMesesAnos);
         Element codigoIbgeMunLogradouro = document.createElement("CODIGOIBGEMUNLOGRADOURO");
         filiacaoNascimento.appendChild(codigoIbgeMunLogradouro);
         Element logradouro = document.createElement("LOGRADOURO");
         filiacaoNascimento.appendChild(logradouro);
         Element numeroLogradouro = document.createElement("NUMEROLOGRADOURO");
         filiacaoNascimento.appendChild(numeroLogradouro);
         Element complementoLogradouro = document.createElement("COMPLEMENTOLOGRADOURO");
         filiacaoNascimento.appendChild(complementoLogradouro);
         Element bairro = document.createElement("BAIRRO");
         filiacaoNascimento.appendChild(bairro);
         Element nacionalidade = document.createElement("NACIONALIDADE");
         filiacaoNascimento.appendChild(nacionalidade);
         Element domicilioEstrangeiro = document.createElement("DOMICILIOESTRANGEIRO");
         filiacaoNascimento.appendChild(domicilioEstrangeiro);
         Element codigoIbgeMunNaturalidade = document.createElement("CODIGOIBGEMUNNATURALIDADE");
         filiacaoNascimento.appendChild(codigoIbgeMunNaturalidade);
         Element textLivreMunicipioNat = document.createElement("TEXTOLIVREMUNICIPIONAT");
         filiacaoNascimento.appendChild(textLivreMunicipioNat);
         Element codigoOcupacaoSdc = document.createElement("CODIGOOCUPACAOSDC");
         filiacaoNascimento.appendChild(codigoOcupacaoSdc);
    }

    public static int calculaDígito(String base) {
        int soma = 0;
        int peso = base.length() + 1;

        for (char c : base.toCharArray()) {
            int dígito = Character.getNumericValue(c);
            soma += dígito * (peso--);
        }

        int resto = (soma * 10) % 11;
        return (resto == 10) ? 1 : resto;
    }
}
