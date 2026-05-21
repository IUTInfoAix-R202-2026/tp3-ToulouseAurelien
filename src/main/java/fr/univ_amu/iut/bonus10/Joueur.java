package fr.univ_amu.iut.bonus10;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

/**
 * Bonus 10 - étape 1 : représentation des joueurs.
 *
 * <p>Cette classe permet de conserver les informations sur les deux joueurs d'une partie d'Othello.
 * Elle a la responsabilité principale de gérer le score des joueurs. Pour éviter d'avoir à
 * manipuler des références nulles, un joueur virtuel {@code PERSONNE} est introduit (il représente
 * une case vide).
 *
 * <p>Conformément à la sémantique d'Othello, la valeur du score se met à jour à chaque coup :
 * lorsqu'un joueur capture un groupe de pions adverses, ces derniers changent de couleur et les
 * scores des deux joueurs varient en miroir.
 */
public class Joueur {

  public static final Joueur PERSONNE = new Joueur("vide.png");
  public static final Joueur NOIR = new Joueur("noir.png");
  public static final Joueur BLANC = new Joueur("blanc.png");

  private Image image;
  private final IntegerProperty score = new SimpleIntegerProperty(this, "score", 0);

  /**
   * Constructeur privé (les trois joueurs sont des singletons obtenus par les constantes statiques
   * {@link #NOIR}, {@link #BLANC} et {@link #PERSONNE}).
   */
  private Joueur(String fileName) {
    this.image = new Image(getClass().getResourceAsStream(fileName));
  }

  /**
   * Réinitialise à zéro les scores des deux joueurs NOIR et BLANC. À appeler en début de partie.
   */
  public static void initialiserScores() {
    NOIR.scoreProperty().set(0);
    BLANC.scoreProperty().set(0);
  }

  /** Image du pion à dessiner sur une case appartenant à ce joueur. */
  public Image getImage() {
    return image;
  }

  /** Propriété observable du score. Le contrôleur s'y liera pour afficher le score à l'écran. */
  public IntegerProperty scoreProperty() {
    return score;
  }

  /** Valeur courante du score (raccourci de {@code scoreProperty().get()}). */
  public int getScore() {
    return score.get();
  }

  /** Incrémente le score du joueur d'une unité. */
  void incrementerScore() {
    score.set(score.get() + 1);
  }

  /** Décrémente le score du joueur d'une unité. */
  void decrementerScore() {
    score.set(score.get() - 1);
  }

  /**
   * Retourne le joueur adverse de celui-ci.
   *
   * <p>Utile pour passer la main au tour suivant ou pour identifier la couleur des pions à
   * capturer.
   */
  public Joueur suivant() {
    if (this == NOIR) {
      return BLANC;
    }
    if (this == BLANC) {
      return NOIR;
    }
    return PERSONNE;
  }
}
