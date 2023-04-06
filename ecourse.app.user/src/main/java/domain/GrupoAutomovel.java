package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GrupoAutomovel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idGrupoAutomovel;

    private String classe;
    private int nrPortas;

    private String nome;

    public GrupoAutomovel() {
    }

    public GrupoAutomovel(String nome, int nrPortas, String classe) {
        this.classe = classe;
        this.nrPortas = nrPortas;
        this.nome = nome;
    }

    public void changeNrPortas(int nrPortas) {
        this.nrPortas = nrPortas;
    }

    public void changeClasse(String classe) {
        this.classe = classe;
    }

    @Override
    public String toString() {
        return "domain.GrupoAutomovel{" +
                "classe='" + classe + '\'' +
                ", nrPortas=" + nrPortas +
                '}';
    }
}
