package model.deck;

import java.util.List;
import java.util.function.Consumer;

import model.player.Player;
/**
 *
 */
public enum SimpleEffect implements Effect {


    REFULL_LIFE_MEDIUM_ME(Target.ME, p -> p.rechargeLife(MEDIUM)),
    REFULL_LIFE_HIGH_ME(Target.ME, p -> p.rechargeLife(HIGH)),
    REFULL_LIFE_SMALL_ME(Target.ME, p -> p.rechargeLife(SMALL)),

    TAKE_DAMAGES_SMALL_OTHER(Target.OTHER, p -> p.takeDamages(SMALL)),
    TAKE_DAMAGES_MEDIUM_OTHER(Target.OTHER, p -> p.takeDamages(MEDIUM)),
    TAKE_DAMAGES_HIGH_OTHER(Target.OTHER, p -> p.takeDamages(HIGH)),
    TAKE_DAMAGES_MEDIUM_ALL(Target.ALL, p -> p.takeDamages(MEDIUM)),


    INCREASE_POINTS_SMALL_ME(Target.ME, p -> p.increasePoints(SMALL)),
    INCREASE_POINTS_MEDIUM_ME(Target.ME, p-> p.increasePoints(MEDIUM)),
    INCREASE_POINT_HIGH_ME(Target.ME, p -> p.increasePoints(HIGH)),
    DECREASE_POINTS_MAX_ALL(Target.ALL, p -> p.decreasePoints(MAX)),
    DECREASE_POINTS_SMALL_OTHER(Target.OTHER, p -> p.decreasePoints(SMALL)),

    INCREASE_MONEY_MEDIUM_ME(Target.ME, p -> p.earnMoney(MEDIUM)),
    INCREASE_MONEY_SMALL_ME(Target.ME, p ->  p.earnMoney(SMALL)),
    DECREASE_MONEY_SMALL_OTHER(Target.OTHER, p ->  p.loseMoney(SMALL)),
    DECREASE_MONEY_SMALL_ALL(Target.ALL, p ->  p.loseMoney(SMALL)),

    //EFFETTI NEGATIVI SULLA VITA
    //tu perdi vita
    TU_PERDI_1_VITA(Target.ME, p ->  p.takeDamages(MIN)),
    TU_PERDI_2_VITE(Target.ME, p ->  p.takeDamages(SMALL)),
    TU_PERDI_3_VITE(Target.ME, p ->  p.takeDamages(MEDIUM)),

    //tutti perdono vita
    TUTTI_PERDONO_1_VITA(Target.ALL, p ->  p.takeDamages(MIN)),
    TUTTI_PERDONO_2_VITE(Target.ALL, p ->  p.takeDamages(SMALL)),
    TUTTI_PERDONO_3_VITE(Target.ALL, p ->  p.takeDamages(MEDIUM)),

    //gli altri perdono vita
    GLI_ALTRI_MOSTRI_PERDONO_1_VITA(Target.OTHER, p ->  p.takeDamages(MIN)),
    GLI_ALTRI_MOSTRI_PERDONO_2_VITE(Target.OTHER, p ->  p.takeDamages(SMALL)),
    GLI_ALTRI_MOSTRI_PERDONO_3_VITE(Target.OTHER, p ->  p.takeDamages(MEDIUM)),

    //EFFETTI POSITIVI ULLA VITA
    //tu guadagni vita
    TU_GUADAGNI_1_VITA(Target.ME, p ->  p.rechargeLife(MIN)),
    TU_GUADAGNI_2_VITE(Target.ME, p ->  p.rechargeLife(SMALL)),
    TU_GUADAGNI_3_VITE(Target.ME, p ->  p.rechargeLife(MEDIUM)),
    TU_GUADAGNI_4_VITE(Target.ME, p ->  p.rechargeLife(HIGH)),

    //EFFETTI SUI SOLDI
    //gli altri
    GLI_ALTRI_MOSTRI_PERDONO_2_SOLDI(Target.OTHER, p -> p.loseMoney(SMALL)),
    GLI_ALTRI_MOSTRI_PERDONO_3_SOLDI(Target.OTHER, p -> p.loseMoney(MEDIUM)),
    //tu
    TU_GUADAGNI_5_SOLDI(Target.ME, p ->  p.earnMoney(MAX)),
    //EFFETTI sui punti
    //negativi
    //tu
    TU_PERDI_1_PUNTO(Target.ME,p -> p.decreasePoints(MIN)),
    TU_PERDI_2_PUNTI(Target.ME,p -> p.decreasePoints(SMALL)),
    TU_PERDI_3_PUNTI(Target.ME,p -> p.decreasePoints(MEDIUM)),
    //altri
    GLI_ALTRI_MOSTRI_PERDONO_1_PUNTO(Target.OTHER, p-> p.decreasePoints(MIN)),
    GLI_ALTRI_MOSTRI_PERDONO_2_PUNTI(Target.OTHER, p-> p.decreasePoints(SMALL)),
    GLI_ALTRI_MOSTRI_PERDONO_3_PUNTI(Target.OTHER, p-> p.decreasePoints(MEDIUM)),
    GLI_ALTRI_MOSTRI_PERDONO_4_PUNTI(Target.OTHER, p-> p.decreasePoints(HIGH)),
    //tutti
    TUTTI_PERDONO_1_PUNTO(Target.ALL, p-> p.decreasePoints(MIN)),
    TUTTI_PERDONO_2_PUNTI(Target.ALL, p-> p.decreasePoints(SMALL)),
    TUTTI_PERDONO_3_PUNTI(Target.ALL, p-> p.decreasePoints(MEDIUM)),
    TUTTI_PERDONO_4_PUNTI(Target.ALL, p-> p.decreasePoints(HIGH)),

    //positivi
    //tu
    TU_GUADAGNI_1_PUNTO(Target.ME, p-> p.increasePoints(MIN)),
    TU_GUADAGNI_2_PUNTI(Target.ME, p-> p.increasePoints(SMALL)),
    TU_GUADAGNI_3_PUNTI(Target.ME, p-> p.increasePoints(MEDIUM)),
    TU_GUADAGNI_4_PUNTI(Target.ME, p-> p.increasePoints(HIGH)),

    //tutti
    TUTTI_GUADAGNANO_1_PUNTO(Target.ALL, p-> p.increasePoints(MIN)),
    TUTTI_GUADAGNANO_2_PUNTI(Target.ALL, p-> p.increasePoints(SMALL)),

    //altri
    GLI_ALTRI_MOSTRI_GUADAGNANO_1_PUNTO(Target.OTHER, p-> p.increasePoints(MIN)),
    GLI_ALTRI_MOSTRI_GUADAGNANO_2_PUNTI(Target.OTHER, p-> p.increasePoints(SMALL));

    private Target t;
    private Consumer<Player> f;

    private SimpleEffect(final Target t, final Consumer<Player> f) {
        this.t = t;
        this.f = f;
    }

    @Override
    public void active(final Player player, final List<Player> other) {
        switch (this.t) {
        case ALL:
            f.accept(player);
            other.forEach(p -> f.accept(p));
            break;
        case ME:
            f.accept(player);
            break;
        case OTHER:
            other.forEach(p -> f.accept(p));
            break;
        default:
            break;
        }
    }

    @Override
    public String toString() {
        return this.name().substring(0, 1) + this.name().substring(1, this.name().length()).toLowerCase().replaceAll("_", " ");
    }

}
