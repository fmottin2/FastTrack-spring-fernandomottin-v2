package br.com.fasttrack.desafio.config.validation;

public class ErrorRequestDTO {

	private String campo;
	private String erro;
	
	public ErrorRequestDTO(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
}
