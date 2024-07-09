package br.com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record BookDTO(@JsonAlias({"title"}) String title,
                      @JsonAlias({"authors"}) List<AuthorDTO> authors,
                      @JsonAlias("languages") List<String> languages,
                      @JsonAlias("download_count") Integer numberOfDownloadsS) {}