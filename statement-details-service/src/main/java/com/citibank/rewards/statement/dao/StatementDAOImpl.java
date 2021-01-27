package com.citibank.rewards.statement.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.citibank.rewards.statement.constant.StatementConstant;
import com.citibank.rewards.statement.exception.BusinessException;
import com.citibank.rewards.statement.exception.SystemException;
import com.citibank.rewards.statement.model.StatementDaoRequest;
import com.citibank.rewards.statement.model.StatementDaoResponse;
import com.citibank.rewards.statement.model.StatementsDetails;

public class StatementDAOImpl implements StatementDAO {

	public StatementDaoResponse getStatements(StatementDaoRequest daoRequest) {

		System.out.println("Entered into DAO Response :" + daoRequest);
		// 1. get the request from service
		// 2. prepare the request for db
		// 3. call statement db and get the response
		// 4. validate the response
		// 5. if it is success prepare the dao response else handle the exceptions

		System.out.println("Entered into dao :" + daoRequest);

		StatementDaoResponse daoResp = new StatementDaoResponse();
		try {
			String env = System.getProperty("env");
			String fileName = "properties/db/transaction-db-" + env + ".properties";

			System.out.println("env : " + env + " fileName :" + fileName);

			Class.forName("com.mysql.jdbc.Driver");
			InputStream input = StatementDAOImpl.class.getClassLoader().getResourceAsStream(fileName);

			Properties properties = new Properties();

			properties.load(input);

			String url = properties.getProperty(StatementConstant.DB_URL);
			String uname = properties.getProperty(StatementConstant.USER_NAME);
			String pwd = properties.getProperty(StatementConstant.PASSWORD);

			Connection connection = DriverManager.getConnection(url, uname, pwd);
			String sql = StatementConstant.SP_CALL;
			// csmt object
			CallableStatement cs = connection.prepareCall(sql);
			// prepare the input params
			cs.setString(1, daoRequest.getClientId());
			cs.setString(2, daoRequest.getChannelId());
			cs.setString(3, daoRequest.getCardNum());
			cs.setString(4, daoRequest.getCvv());
			cs.setString(5, daoRequest.getExpDate());
			cs.setString(6, daoRequest.getNameOnCard());

			// register out params
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.registerOutParameter(8, Types.VARCHAR);

			// call DB and get the result set

			cs.execute();
			ResultSet rs = cs.executeQuery();

			String dbRespCode = cs.getString(7); // get it from backend
			String dbRespMsg = cs.getString(8);

			daoResp.setRespCode(dbRespCode);
			daoResp.setRespMsg(dbRespMsg);

			List<StatementsDetails> statementsDetailsList = new ArrayList<StatementsDetails>();

			if ("000".equals(dbRespCode)) {
				// prepare the dao response. i.e convert ResultSet response into dao response
				while (rs.next()) {

					StatementsDetails statementsDetails = new StatementsDetails();
					statementsDetails.setTxnId(rs.getString(1));
					statementsDetails.setTxnname(rs.getString(2));
					statementsDetails.setDesc(rs.getString(3));
					statementsDetails.setAmount(rs.getString(4));
					statementsDetails.setMerchantName(rs.getString(5));
					statementsDetails.setRemarks(rs.getString(6));
					statementsDetails.setRedeemPoints(rs.getString(7));

					statementsDetailsList.add(statementsDetails);
				}
			} else if ("100".equals(dbRespCode) || "101".equals(dbRespCode) || "102".equals(dbRespCode)
					|| "103".equals(dbRespCode)) {

				throw new BusinessException(dbRespCode, dbRespMsg);

			} else {
				throw new SystemException(dbRespCode, dbRespMsg);
			}

			daoResp.setStatementsDetails(statementsDetailsList);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Exit  from  DAO Response :" + daoResp);

		return daoResp;
	}
	
	public static void main(String[] args) {
		
		System.setProperty("env", "dev");
		StatementDaoRequest daoRequest = new StatementDaoRequest();
		
		daoRequest.setClientId("web");
		daoRequest.setChannelId("online");
		daoRequest.setCardNum("05211140058239");
		daoRequest.setCvv("123");
		daoRequest.setNameOnCard("sreenu");
		daoRequest.setExpDate("12-2020");
		
		StatementDAO dao = new StatementDAOImpl();
		
		StatementDaoResponse daoResp = dao.getStatements(daoRequest);
		
		System.out.println("daoResp is :"+daoResp);
		
	}

}
