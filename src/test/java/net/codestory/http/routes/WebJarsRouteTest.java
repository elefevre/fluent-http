package net.codestory.http.routes;

import static org.assertj.core.api.Assertions.*;

import java.net.*;

import org.junit.*;

public class WebJarsRouteTest {
  @Test
  public void dont_use_minified_asset_in_dev_mode() {
    WebJarsRoute webJarsFilter = new WebJarsRoute(false);

    URL url = webJarsFilter.findUrl("/webjars/bootstrap/3.1.0/js/bootstrap.min.js");
    assertThat(url.toString()).endsWith("/META-INF/resources/webjars/bootstrap/3.1.0/js/bootstrap.js");

    url = webJarsFilter.findUrl("/webjars/bootstrap/3.1.0/js/bootstrap.js");
    assertThat(url.toString()).endsWith("/META-INF/resources/webjars/bootstrap/3.1.0/js/bootstrap.js");
  }

  @Test
  public void non_minified_version_doesnt_exists() {
    WebJarsRoute webJarsFilter = new WebJarsRoute(false);

    URL url = webJarsFilter.findUrl("/webjars/coffee-script/1.7.0/coffee-script.min.js");
    assertThat(url.toString()).endsWith("/META-INF/resources/webjars/coffee-script/1.7.0/coffee-script.min.js");

    url = webJarsFilter.findUrl("/webjars/coffee-script/1.7.0/coffee-script.js");
    assertThat(url.toString()).endsWith("/webjars/coffee-script/1.7.0/coffee-script.min.js");
  }

  @Test
  public void use_minified_asset_in_production_mode() {
    WebJarsRoute webJarsFilter = new WebJarsRoute(true);

    URL url = webJarsFilter.findUrl("/webjars/bootstrap/3.1.0/js/bootstrap.min.js");
    assertThat(url.toString()).endsWith("/META-INF/resources/webjars/bootstrap/3.1.0/js/bootstrap.min.js");

    url = webJarsFilter.findUrl("/webjars/bootstrap/3.1.0/js/bootstrap.js");
    assertThat(url.toString()).endsWith("/META-INF/resources/webjars/bootstrap/3.1.0/js/bootstrap.min.js");
  }

  @Test
  public void minified_version_doesnt_exists() {
    WebJarsRoute webJarsFilter = new WebJarsRoute(true);

    URL url = webJarsFilter.findUrl("/webjars/restangular/1.2.2/webjars-requirejs.min.js");
    assertThat(url.toString()).endsWith("/META-INF/resources/webjars/restangular/1.2.2/webjars-requirejs.js");

    url = webJarsFilter.findUrl("/webjars/restangular/1.2.2/webjars-requirejs.js");
    assertThat(url.toString()).endsWith("/META-INF/resources/webjars/restangular/1.2.2/webjars-requirejs.js");
  }
}
