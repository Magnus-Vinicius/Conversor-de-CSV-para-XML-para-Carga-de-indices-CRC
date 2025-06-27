package conversorcsvcargaxml;

public class CalculoMatricula {
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

    public static String calculaMatricula(
            String cns,
            String acervo,
            String ano,
            String tipoAto,
            String livro,
            String folha,
            String registro) {

        cns = String.format("%06d", Integer.valueOf(cns));
        acervo = String.format("%02d", Integer.valueOf(acervo));
        livro = String.format("%05d", Integer.valueOf(livro));
        folha = String.format("%03d", Integer.valueOf(folha));
        registro = String.format("%07d", Integer.valueOf(registro));

        String base = cns + acervo + "55" + ano + tipoAto + livro + folha + registro;

        int d1 = calculaDígito(base);
        int d2 = calculaDígito(base + d1);
        return base + d1 + d2;
    }
}
