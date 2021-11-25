package br.com.fasttrack.desafio.entity;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum StatusOrder {

	PROCESSED,
	@JsonEnumDefaultValue
	NOT_PROCESSED;
}
