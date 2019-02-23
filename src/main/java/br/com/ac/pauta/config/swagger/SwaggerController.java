package br.com.ac.pauta.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Optional;


/**
 * @author Alex Carvalho
 */
@RestController
@ApiIgnore
public class SwaggerController {

    private final Optional<BuildProperties> buildProperties;

    @Value("classpath:templates/home.html")
    private Resource homeTemplate;

    private String template;

    public SwaggerController(Optional<BuildProperties> buildProperties) {
        this.buildProperties = buildProperties;
    }


    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String index() throws IOException {
        return template != null ? template : loadTemplate();
    }

    private String loadTemplate() throws IOException {
        File templateFile = homeTemplate.getFile();

        template = new String(Files.readAllBytes(templateFile.toPath()), Charset.forName("UTF-8"));

        template = buildProperties.map(entries -> template
                .replace("$version", entries.getVersion())
                .replace("$timestamp", entries.getTime().toString()))
                .orElse(template);

        return template;
    }
}
