package fr.univ_amu.iut.bonus8;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Region;

/**
 * Contrôleur du bonus 8 - bascule de thème.
 *
 * <p>Le clic sur le {@link ToggleButton} remplace la feuille CSS courante de la {@link
 * javafx.scene.Scene} : {@code theme-clair.css} si non sélectionné, {@code theme-sombre.css} sinon.
 */
public class ThemeToggleController {

  static final String CSS_CLAIR = "theme-clair.css";
  static final String CSS_SOMBRE = "theme-sombre.css";

  @FXML private ToggleButton boutonTheme;

  @FXML private Region racine;

  @FXML
  private void initialize() {
    boutonTheme
        .selectedProperty()
        .addListener(
            (obs, ancien, selectionne) -> {
              String css = selectionne ? CSS_SOMBRE : CSS_CLAIR;
              String url = getClass().getResource(css).toExternalForm();
              if (racine.getScene() != null) {
                racine.getScene().getStylesheets().setAll(url);
              }
              if (selectionne) {
                boutonTheme.setText("☀ Mode clair");
              } else {
                boutonTheme.setText("🌙 Mode sombre");
              }
            });
  }
}
