package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos;

import javax.persistence.*;

@Entity
public class CaracteristicasProdutos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public CaracteristicasProdutos() {
    }

    public CaracteristicasProdutos(String nome, String descricao, Produto produto) {

        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CaracteristicasProdutos)) return false;

        CaracteristicasProdutos that = (CaracteristicasProdutos) o;

        if (!nome.equals(that.nome)) return false;
        return produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        int result = nome.hashCode();
        result = 31 * result + produto.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CaracteristicasProdutos{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
