package dados;

import java.sql.SQLException;

import util.Codigo;

public class CodigosBD extends PrepararSql{

	public CodigosBD() throws SQLException, ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Codigo buscar(String campo) throws SQLException {
		// TODO Auto-generated method stub
		Codigo codigo = new Codigo();
		
		buscar.setString(1, "codigos");
		buscar.setString(2,campo);
		buscar.setString(3, "1");
		
		rs = buscar.executeQuery();
		rs.next();
		codigo.setId(rs.getInt("id"));
		codigo.setCodigo_endereco(rs.getInt("cod_endereco"));
		codigo.setCodigo_funcionario(rs.getInt("cod_funcionario"));
		codigo.setCodigo_cliente(rs.getInt("cod_cliente"));
		codigo.setCodigo_tipo(rs.getInt("cod_tipo"));
		codigo.setCodigo_pagforma(rs.getInt("cod_pagforma"));
		
		return codigo;
	}

	@Override
	public void atualizar(String campo) throws SQLException {
		// TODO Auto-generated method stub
		int atualiza = 0;
		
		switch (campo){
		case "cod_endereco":
			atualiza = buscar(campo).getCodigo_endereco();
			break;
		case "cod_funcionario":
			atualiza = buscar(campo).getCodigo_funcionario();
			break;
		case "cod_cliente":
			atualiza = buscar(campo).getCodigo_cliente();
			break;
		case "cod_tipo":
			atualiza = buscar(campo).getCodigo_tipo();
			break;
		case "cod_pagforma":
			atualiza = buscar(campo).getCodigo_pagforma();
			break;
		}
		
			atualiza = atualiza +1;
		
		atualizar.setString(1,"codigos");
		atualizar.setString(2,campo);
		atualizar.setInt(3,atualiza);
		atualizar.setInt(4,1);
		
		
	}
	
	

}
