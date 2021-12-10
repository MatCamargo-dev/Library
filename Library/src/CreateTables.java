import java.sql.*;
import java.util.Properties;

public class CreateTables {
    public void create() {
        Properties connConfig = new Properties();
        connConfig.setProperty("user", "root");
        connConfig.setProperty("password", "123456");

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3308", connConfig)) {

            //Disable auto-commit
            conn.setAutoCommit(false);

            // book
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.books (" +
                                "authorId INT PRIMARY KEY AUTO_INCREMENT," +
                                "title VARCHAR(25)," +
                                "author VARCHAR(25)," +
                                "fkpublisherId INT NOT NULL," +
                                "fkauthorId INT NOT NULL," +
                                "CONSTRAINT FK_publisherId FOREING KEY (fkpublisherId)" +
                                "REFERENCES LIBRARY.publisher(publisherId)," +
                                "CONSTRAINT FK_authorId FOREING KEY (fkauthorId)" +
                                "REFERENCES LIBRARY.author(authorId)," +
                                "ENGINE=innoDB;"
                );

                try (PreparedStatement prep = conn.prepareStatement(
                        "INSERT INTO library.books (title, author) VALUES (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                )) {
                    //add book
                    prep.setString(1, "Cem anos de solidao");
                    prep.setString(2, "Gabriel Garcia Marques");
                    prep.addBatch();

                    //add book
                    prep.setString(1, "Os trabalhadores do mar");
                    prep.setString(2, "Victor Hugo");
                    prep.addBatch();

                    System.out.println("Batch Counts");
                    int[] updateCounts = prep.executeBatch();
                    for (int count : updateCounts) {
                        System.out.println(count);
                    }
                }

                try (PreparedStatement prep = conn.prepareStatement(
                        "UPDATE library.books " +
                                "$ET author = ? WHERE authorId = ?"
                )) {
                    prep.setString(1, "Joao");

                    prep.setInt(2, 1);
                    prep.execute();
                }

                try (PreparedStatement prep = conn.prepareStatement(
                        "DELETE FROM library.books" +
                                "WHERE authorId = ?"
                )) {
                    prep.setInt(1, 3);
                    prep.execute();
                }

                conn.commit();

                try (ResultSet _list = stmt.executeQuery(
                        "SELECT authorId, title, author + " +
                                "FROM library.books")) {

                    System.out.println("Books: ");
                    while (_list.next()) {
                        System.out.println(String.format("%d %s %s",
                                _list.getInt("authorId"),
                                _list.getString("title"),
                                _list.getString("author")));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // publisher
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.publisher (" +
                                "publisherId INT PRIMARY KEY AUTO_INCREMENT," +
                                "name VARCHAR(25)," +
                                "adress VARCHAR(25)," +
                                "ENGINE=innoDB;"
                );
                try (PreparedStatement prep = conn.prepareStatement(
                        "INSERT INTO library.publisher (name, adress) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                )) {
                    //add publisher
                    prep.setString(1, "Joao");
                    prep.setString(2, "Jose Maria");
                    prep.addBatch();

                    //add publisher
                    prep.setString(1, "Gustavo");
                    prep.setString(2, "Jardim Soares");
                    prep.addBatch();

                    System.out.println("Batch Counts");
                    int[] updateCounts = prep.executeBatch();
                    for (int count : updateCounts) {
                        System.out.println(count);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            // author
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.author (" +
                                "authorId INT PRIMARY KEY AUTO_INCREMENT," +
                                "name VARCHAR(25)," +
                                "adress VARCHAR(25)," +
                                "ENGINE=innoDB;"
                );
                try (PreparedStatement prep = conn.prepareStatement(
                        "INSERT INTO library.author (name, adress) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                )) {
                    //add publisher
                    prep.setString(1, "Francisco");
                    prep.setString(2, "Sao Vicente");
                    prep.addBatch();

                    //add publisher
                    prep.setString(1, "Frederico");
                    prep.setString(2, "Jardim Paulista");
                    prep.addBatch();

                    System.out.println("Batch Counts");
                    int[] updateCounts = prep.executeBatch();
                    for (int count : updateCounts) {
                        System.out.println(count);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // shipping Info
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.shippingInfo (" +
                                "adress VARCHAR(25)," +
                                "fkshipperId INT NOT NULL," +
                                "CONSTRAINT FK_shipperId FOREING KEY (fkshipperId)" +
                                "REFERENCES LIBRARY.shipper(shipperId)," +
                                "ENGINE=innoDB;"
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            // shipper
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.shipper (" +
                                "shipperId INT PRIMARY KEY AUTO_INCREMENT," +
                                "name VARCHAR(25)," +
                                "ENGINE=innoDB;"
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            // billing Info
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.billingInfo (" +
                                "billingInfoId INT PRIMARY KEY AUTO_INCREMENT," +
                                "ENGINE=innoDB;"
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            // account
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.account (" +
                                "accountIdTemp INT PRIMARY KEY AUTO_INCREMENT," +
                                "accountId INT PRIMARY KEY AUTO_INCREMENT," +
                                "emailAddress VARCHAR(25)," +
                                "password VARCHAR(100)," +
                                "passwordTemp VARCHAR(100)," +
                                "fkbillingInfoId INT NOT NULL," +
                                "CONSTRAINT FK_fkbillingInfoId FOREING KEY (fkbillingInfoId)" +
                                "REFERENCES LIBRARY.billingInfo(billingInfoId)," +
                                "fkuserId INT NOT NULL," +
                                "CONSTRAINT FK_fkuserId FOREING KEY (fkuserId)" +
                                "REFERENCES LIBRARY.user(userId)," +
                                "ENGINE=innoDB;"
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            // user
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.user (" +
                                "userId INT PRIMARY KEY AUTO_INCREMENT," +
                                "name VARCHAR(25)," +
                                "fkeditorialId INT NOT NULL," +
                                "CONSTRAINT FK_fkeditorialId FOREING KEY (editorialId)" +
                                "REFERENCES LIBRARY.editorial(editorialId)," +
                                "fkcustomerId INT NOT NULL," +
                                "CONSTRAINT FK_fkcustomerId FOREING KEY (customerId)" +
                                "REFERENCES LIBRARY.customer(customerId)," +
                                "ENGINE=innoDB;"
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            // editorial
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.editorial (" +
                                "editorialId INT PRIMARY KEY AUTO_INCREMENT," +
                                "name VARCHAR(25)," +
                                "ENGINE=innoDB;"
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            // customer
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.customer (" +
                                "customerlId INT PRIMARY KEY AUTO_INCREMENT," +
                                "name VARCHAR(25)," +
                                "ENGINE=innoDB;"
                );
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }
}
