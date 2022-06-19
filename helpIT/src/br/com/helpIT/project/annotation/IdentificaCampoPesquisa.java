package br.com.helpIT.project.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation para identificar os campos que podem aparecer na pesquisa das telas
 * @author Cristiano Aragão
 *
 */

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public abstract @interface IdentificaCampoPesquisa {

	String descricaoCampo(); // descrição do campos para a tela
	String campoConsulta(); // campo do banco
	int principal() default 10000; // posição que vai aparecer no combo, 10000 é para que o default seja colocado depois dos que são estabelecidos
	
}
