package br.com.agenda.BancodeDados;

import br.com.agenda.Modelo.Atributos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class BancoNegocios {

    private final Conexao conn;
    private PreparedStatement ps = null;

    public BancoNegocios() {
        conn = new Conexao();
    }

    public void inserir(Atributos at) {
        String query = "INSERT INTO usuario(nome,endereco,email,residencial,telefone,cpf,sobre) VALUES(?,?,?,?,?,?,?)";
        try {
            ps = conn.getConectMySQl().prepareStatement(query);
            ps.setString(1, at.getNome());
            ps.setString(2, at.getEnd());
            ps.setString(3, at.getEmail());
            ps.setString(4, at.getResi());
            ps.setString(5, at.getTel());
            ps.setString(6, at.getCpf());
            ps.setString(7, at.getSobre());

            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Dados gravados com sucesso!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                ps.close();
                conn.getConectMySQl().close();
            }

        } catch (NullPointerException nx) {
            JOptionPane.showMessageDialog(null, " Reveja os campos que estão vazio! ", " Atenção! ", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Não houve resposta do banco! ", "Atenção!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Atualizar(Atributos at) {
        String query = " UPDATE usuario SET nome = ?, endereco = ?, email = ?, residencial = ?, telefone = ?, cpf = ?, sobre = ?"
                + " WHERE id = ?";
        try {
            ps = conn.getConectMySQl().prepareStatement(query);
            ps.setString(1, at.getNome());
            ps.setString(2, at.getEnd());
            ps.setString(3, at.getEmail());
            ps.setString(4, at.getResi());
            ps.setString(5, at.getTel());
            ps.setString(6, at.getCpf());
            ps.setString(7, at.getSobre());
            ps.setInt(8, at.getId());

            int r = ps.executeUpdate();
            if (r != 0) {
                JOptionPane.showMessageDialog(null, "Contato Atualizado!!");
                ps.close();
                conn.getConectMySQl().close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Opá! Erro ao atualizar " + ex.getSQLState() + ex.getErrorCode());
        }
    }

    public void Deletar(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try {
            ps = conn.getConectMySQl().prepareStatement(sql);
            try {
                ps.setInt(1, id);
                if (ps.executeUpdate() != 0) {
                    JOptionPane.showMessageDialog(null, "Contato deletado com sucesso!");
                    ps.close();
                    conn.getConectMySQl().close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void ApagarAgenda() {
        try {
            Statement stmt = this.conn.getConectMySQl().createStatement();
            String sql = "drop table if exists usuario;";
            String sql2 = "create table if not exists usuario("
                    + "\n id int not null auto_increment,"
                    + "\n nome varchar(100),"
                    + "\n endereco varchar(100),"
                    + "\n email varchar(100),"
                    + "\n residencial varchar(30),"
                    + "\n telefone varchar(100),"
                    + "\n cpf varchar(100),"
                    + "\n sobre varchar(255),"
                    + "\n constraint usuario_id_pk primary key(id),"
                    + "\n constraint usuario_cpf_uk unique (cpf)\n);";
            int r = JOptionPane.showConfirmDialog(null,
                    " Essa ação apagará toda a Agenda. Quer continuar ? ", " ", JOptionPane.YES_NO_CANCEL_OPTION);
            if (r == 0) {
                stmt.executeUpdate(sql);
                stmt.executeUpdate(sql2);
                stmt.close();
                JOptionPane.showMessageDialog(null, " Agenda apagada! ");
            } else {
                JOptionPane.showMessageDialog(null, " Problemas na hora de execultar esta ação ", "", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            if (conn.getConectMySQl() != null && ps != null) {
                try {
                    conn.getConectMySQl().close();
                    ps.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }

    public List<Atributos> getContatos(String nome) {
        String sql = "SELECT * FROM usuario WHERE nome LIKE '%" + nome + "%'";
        List<Atributos> lista = new ArrayList<>();
        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;
        try {
            ps = conn.getConectMySQl().prepareStatement(sql);
            //ps.setString(1, nome);
            rset = ps.executeQuery();
            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                Atributos at = new Atributos();
                //Recupera o id do banco e atribui ele ao objeto
                at.setId(rset.getInt("id"));
                //Recupera o nome do banco e atribui ele ao objeto
                at.setNome(rset.getString("nome"));
                //Recupera a endereco do banco e atribui ele ao objeto
                at.setEnd(rset.getString("endereco"));
                //Recupera o email do banco e atribui ela ao objeto
                at.setEmail(rset.getString("email"));
                //Recupera o telefone residencial
                at.setResi(rset.getString("residencial"));
                //Recupera o telefone celular
                at.setTel(rset.getString("telefone"));
                //Recupera o cpf
                at.setCpf(rset.getString("cpf"));
                //Recupera a descrição do usuario
                at.setSobre(rset.getString("sobre"));
                //Adiciono o contato recuperado, a lista de contatos
                lista.add(at);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + " " + JOptionPane.INFORMATION_MESSAGE);
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.getConectMySQl().close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}
