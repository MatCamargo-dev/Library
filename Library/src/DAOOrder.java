import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAOOrder {
    //faz a insercao da ordem de compra
    String query0 = "INSERT INTO library.order (fkiduser, nome) "
            + "VALUES (?,?)";
    PreparedStatement prep0 = cdb.getConnection().prepareStatement(
            query0, Statement.RETURN_GENERATED_KEYS);
         prep0.setInt(1,order.getUser().

    getId());
         prep0.setString(2,"venda grande");
         prep0.execute();
    //pega o id da ordem de compra
    ResultSet idOrdemCompra = prep0.getGeneratedKeys();
         idOrdemCompra.next();
    int idOC = ultimaID.getInt("id");


    //varre a lista de livros e prepara a insercao em order_itens
    String query1 = "INSERT INTO library.order_itens (fkiduser, fkidproduto, qtde) "
            + "VALUES (?,?,?)";
    PreparedStatement prep1 = cdb.getConnection().prepareStatement(
            query1, Statement.RETURN_GENERATED_KEYS);
    //atualiza a lista de produtos
    String query2 = "UPDATE livraria.livros " +
            "SET qtde = ? WHERE id = ?";

    PreparedStatement prep2 = cdb.getConnection().prepareStatement(
            query2);


    //consulta sql do produto
    String queryLivro = "Select * from livraria.livros " +
            "where id = ?";

    PreparedStatement prepProduto = cdb.getConnection().prepareStatement(
            queryLivros);

    //DAOProduto daoProduto=new DAOProduto();


        for(
    Book b:order.getBooks())

    {

        //Produto p=new Produto();
        //p=daoProduto.consultaPorID(b);
        //faz a consulta no banco pela quantidade do produto
        prepProduto.setInt(1, b.getId());
        ResultSet produtoDoBanco = prepProduto.execute();
        produtoDoBanco.next();
        int qtdeDeProdNoBanco = produtoDoBanco.getInt("qtde");

        //adiciona o produto vinculando no orderitens
        prep1.setInt(1, idOC);
        prep1.setInt(2, b.getID());
        prep1.setInt(3, b.getQtde());
        prep1.addBatch();


        //atualiza a tabela de produtos, decrementando o estoque.
        prep2.setInt(1, qtdeDeProdNoBanco - b.getQtde());
        prep2.setInt(2, b.getId());
        prep2.addBatch();

    }
        prep1.executeBatch();
        prep2.executeBatch();

        cdb.getConnection().

    commit();
        cdb.close();
}

