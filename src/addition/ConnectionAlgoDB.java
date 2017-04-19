package addition;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class ConnectionAlgoDB {
	static String dbUrl = "jdbc:mysql://127.0.0.1:3306/elab?characterEncoding=UTF-8";
	static String dbUsername = "librarian";
	static String dbPassword = "40211";
	ArrayList<Parameter> ParameterAr;
	PriorityQueue<Node> pqforCandidateAlgo;
	public static boolean updateDB = false;

	public ConnectionAlgoDB() {

	}

	public static void updateDB(String table, String name, String className, String inArgType, String outArgType,
			String source, int numInArg, String operSymbol, String methodName) {
		
		source = source.replaceAll("\\\"", "\\\\\"");
		System.out.println("updateDB 실행!");
		Statement statement = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			statement = connection.createStatement();
			// statement.executeUpdate("INSERT INTO `elab`.`"+table+"` (`Name`,
			// `ClassName`, `Source`) VALUES ('"+name+"', '"+className+"',
			// '"+source+"');");
			System.out.println(source);
			statement.executeUpdate("INSERT INTO `elab`.`" + table
					+ "` (`Name`, `ClassName`, `InArgType`, `OutArgType`, `Source`, `NumInArg`, `OperSymbol`, `MethodName`) VALUES ('"
					+ name + "', '" + className + "', '" + inArgType + "', '" + outArgType + "', '" + source + "', '"
					+ numInArg + "', '" + operSymbol + "', '" + methodName + "');");
			updateDB = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DB업데이트!!!!");
	}

	public static ResultSet getRdfs() {
		Statement statement = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			statement = connection.createStatement();
			String str = "select * from rdfs";
			rs = statement.executeQuery(str);
			rs.beforeFirst();
		} catch (SQLException e) {
			System.err.print(e.getMessage() + "SQLException ARGH!");
		} catch (Exception e) {
			System.err.print(e.getMessage() + "Exception FUUUUUUUUUU!");
		}
		return rs;
	}

	public static ResultSet getAlgorithm() {
		Statement statement = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			statement = connection.createStatement();
			String str = "select * from algorithm";
			rs = statement.executeQuery(str);
		} catch (SQLException e) {
			System.err.print(e.getMessage() + "SQLException ARGH!");
		} catch (Exception e) {
			System.err.print(e.getMessage() + "Exception FUUUUUUUUUU!");
		}
		return rs;
	}

	public static ResultSet getCRFModelbyStyleName(String queryOperator) {
		Statement statement = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			statement = connection.createStatement();
			String str = "select * from crfmodels where style ='" + queryOperator + "';";
			rs = statement.executeQuery(str);
		} catch (SQLException e) {
			System.err.print(e.getMessage() + "SQLException ARGH!");
		} catch (Exception e) {
			System.err.print(e.getMessage() + "Exception FUUUUUUUUUU!");
		}
		return rs;
	}

	public static boolean existRelevantAlgorithm(String queryOperator) {
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/elab?characterEncoding=UTF-8";
		String dbUsername = "librarian";
		String dbPassword = "40211";

		boolean output = false;

		Statement statement = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			statement = connection.createStatement();

			String str = "select * from Algorithm where Name='" + queryOperator + "';";
			rs = statement.executeQuery(str);

			System.out.println("query in existRelevantAlgorithm:" + queryOperator);

			String className = "";
			String source = "";
			int count = 0;

			try {
				rs.last();
				count = rs.getRow();
				rs.beforeFirst();
			} catch (Exception e) {
				System.err.println(e.getMessage() + "ARGH!!");
			}

			while (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("Name");
				className = rs.getString("ClassName");
				String inArgType = rs.getString("InArgType");
				String outArgType = rs.getString("OutArgType");
				source = rs.getString("Source");
				System.out.println(
						id + "\t" + name + "\t" + className + "\t" + inArgType + "\t" + outArgType + "\t" + source);
				count++;
			}

			if (count != 0) {
				output = true;
			}

		} catch (SQLException e) {
			System.err.print(e.getMessage() + " ARGH!");
		} catch (Exception e) {
			System.err.print(e.getMessage() + " FUUUUUUUUUU!");
		}
		return output;

	}

	public static ResultSet getRelevantAlgorithm(String queryOperator) {
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/elab?characterEncoding=UTF-8";
		String dbUsername = "librarian";
		String dbPassword = "40211";

		Statement statement = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			statement = connection.createStatement();
			String str = "select count(*) from Algorithm where Name='" + queryOperator + "';";

			rs = statement.executeQuery(str);

			str = "select * from Algorithm where Name='" + queryOperator + "';";
			rs = statement.executeQuery(str);

			System.out.println("query:" + queryOperator);

			String className = "";
			String source = "";

			int count = 0;
			try {
				rs.last();
				count = rs.getRow();
				rs.beforeFirst();
			} catch (Exception ex) {
				System.err.println(ex.getMessage() + "ARGH!!");
			}

			while (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("Name");
				className = rs.getString("ClassName");
				String inArgType = rs.getString("InArgType");
				String outArgType = rs.getString("OutArgType");
				source = rs.getString("Source");
				// System.out.println(
				// id + "\t" + name + "\t" + className + "\t" + inArgType + "\t"
				// + outArgType + "\t" + source);
			}

			rs.beforeFirst();

			if (count != 0) {
				return rs;
			} else
				return null;

		} catch (SQLException e) {
			System.err.print(e.getMessage() + " ARGH!");
		} catch (Exception e) {
			System.err.print(e.getMessage() + " FUUUUUUUUUU!");
		}
		return rs;

	}

	public ResultSet getCandidateAlgorithms(String queryOperator) {
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/elab?characterEncoding=UTF-8";
		String dbUsername = "librarian";
		String dbPassword = "40211";

		Statement statement = null;
		ResultSet rs = null, tempRS = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			statement = connection.createStatement();
			// String str =
			// "select * from Algorithm where Name='"+queryOperator+"';";

			String str = "select B.className, B.Source " + " from elab.Ontology as A , elab.Algorithm as B "
					+ "where (select A.name where (A.BT in (SELECT A.Name  FROM elab.Ontology as A where A.NT ='"
					+ queryOperator + "'))) = B.className;";

			rs = statement.executeQuery(str);

			String className = "";
			String source = "";
			int count = 0;
			try {
				rs.last();
				count = rs.getRow();
				rs.beforeFirst();

			} catch (Exception e) {
				System.err.println(e.getMessage() + "ARGH!!");
			}
			System.out.println("query: " + queryOperator + "\nNumOf Result Set: " + count);

			while (rs.next()) { // String id = rs.getString("className");
				// String name = rs.getString("Name");
				className = rs.getString("ClassName"); // String inArgType =
				// rs.getString("InArgType"); //String outArgType =
				// rs.getString("OutArgType");
				source = rs.getString("Source");
				// System.out.println(id + "\t" + name + "\t" + className +
				// "\t" + inArgType + "\t" + outArgType + // "\t" + source);
				System.out.println(className + "\t" + source);
			}
			if (count != 0) {
				rs.beforeFirst();
				return rs;
			} else
				return null;

		} catch (SQLException e) {
			System.err.print(e.getMessage() + " ARGH!");
		} catch (Exception e) {
			System.err.print(e.getMessage() + " FUUUUUUUUUU!");
		}
		return tempRS;

	}

	private static double measureHowDiffAlgorithmIsFromConstraint(String className, double inArg1, double inArg2,
			double outArg, String source) {
		double output = 0.0;
		File helloWorldJava = new File("./" + className + ".java");// ("./add.java");
		System.out.println("here 5-2 in calculateCostOfAlgorithm");
		if (helloWorldJava.getParentFile().exists() || helloWorldJava.getParentFile().mkdirs()) {

			try {
				Writer writer = null;
				try {
					writer = new FileWriter(helloWorldJava);
					writer.write(source);
					writer.flush();
				} finally {
					try {
						writer.close();
					} catch (Exception e) {
					}
				}

				/**
				 * Compilation Requirements
				 *********************************************************************************************/
				DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
				JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
				StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

				// This sets up the class path that the compiler will use.
				// I've added the .jar file that contains the DoStuff interface
				// within in it...
				List<String> optionList = new ArrayList<String>();
				optionList.add("-classpath");
				optionList.add(System.getProperty("java.class.path") + ";dist/InlineCompiler.jar");

				Iterable<? extends JavaFileObject> compilationUnit = fileManager
						.getJavaFileObjectsFromFiles(Arrays.asList(helloWorldJava));
				JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, optionList, null,
						compilationUnit);
				/*********************************************************************************************
				 * Compilation Requirements
				 **/
				if (task.call()) {
					/**
					 * Load and execute
					 *************************************************************************************************/
					System.out.println("Yipe");
					// Create a new custom class loader, pointing to the
					// directory that contains the compiled
					// classes, this should point to the top of the package
					// structure!
					URLClassLoader classLoader = new URLClassLoader(

							new URL[] { new File("./").toURI().toURL() });
					System.out.println("Yipe2");
					// Load the class from the classloader by name....
					Class<?> loadedClass = classLoader.loadClass(className);// "add"
					System.out.println("Yipe3");
					// Create a new instance...
					Object obj = loadedClass.newInstance();
					classLoader.close();
					System.out.println("Yipe4");
					// Santity check
					if (obj instanceof DoStuff) {
						// Cast to the DoStuff interface
						DoStuff stuffToDo = (DoStuff) obj;
						// Run it baby
						stuffToDo.doStuff();
					}
					/*************************************************************************************************
					 * Load and execute
					 **/
				} else {
					System.out.println("Error");
					for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
						System.out.format("Error on line %d in %s%n", diagnostic.getLineNumber(),
								diagnostic.getSource().toUri());
					}
				}
				fileManager.close();
				System.out.println("here is 6");
			} catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException exp) {
				exp.printStackTrace();
			}
		}
		String strString = "java.lang.Double";// "java.lang.String";
		String strClass = className; // "addition";//"tutorial.HTSCRE";
		String strMethod = className;// "add";//getNodes(infmodel, a ,p);
		try {
			System.out.println("here is 7");
			System.out.println("strClass :" + strClass);

			URLClassLoader classLoader = new URLClassLoader(new URL[] { new File("./").toURI().toURL() });

			Class<?> passedClass = classLoader.loadClass(strClass);// "add"
																	// tutorial.HTSCRE

			// parameter types for methods
			Class<?>[] partypes = new Class[] { Class.forName(strString), Class.forName(strString) }; // input
																										// type
			System.out.println("here is 8");

			// Create method object. method name and parameter types
			Method meth = passedClass.getMethod(strMethod, partypes); // class,
																		// method,
																		// input
																		// type
			System.out.println("here is 9");

			// parameter types for constructor
			Class<?>[] constrpartypes = new Class[] {};
			// Create constructor object. parameter types
			Constructor<?> constr = passedClass.getConstructor(constrpartypes); // class
																				// constructor
			// create instance
			Object dummyto = constr.newInstance(); // create class instance

			// Arguments to be passed into method
			Object[] arglist = new Object[] { (inArg1), (inArg2) };
			// invoke method!!
			System.out.println("here is 10");
			output = (Double) meth.invoke(dummyto, arglist); // method, class
																// instance
			classLoader.close();

			System.out.println("here is 11");
			System.out.println(arglist[0] + " " + className + " " + arglist[1] + " = " + output);

			System.out.println("here is 12");

			return Math.abs((double) (output - outArg));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Math.abs((double) (output - outArg));

	}

	static class Node {
		int id;
		double cost; // error cost with respect to each ground truth set
						// elements
		String name;// node name
		String source;
	}

	static class PriorityQueueAlgorithm implements Comparator<Node> {
		public int compare(Node one, Node two) {
			return (int) (one.cost) - (int) (two.cost);
		}
	}

	public static PriorityQueue<Node> getEvaluationResult(String queryOperator, ResultSet rs1) {

		String className = "";
		String source = "";

		/* Í∞ÄÏû• ÏµúÏÜåÎπÑÏö©ÏùÑ Í∞ÄÏßÄÎäî NodeÎ•º Ï∞æÏïÑ Î¶¨ÌÑ¥ÌïúÎã§ */
		/* A* ÏïåÍ≥†Î¶¨Ï¶òÏùÑ Íµ¨ÌòÑÌïúÎã§ */
		System.out.println("1 in find function ");

		PriorityQueueAlgorithm pqa = new PriorityQueueAlgorithm();
		System.out.println("2 in find function ");

		PriorityQueue<Node> pq = new PriorityQueue<Node>(10, pqa);
		System.out.println("3 in find function ");

		try {

			System.out.println("4 in find function ");

			if (rs1 == null)
				System.out.println("rs1 is null");
			else
				System.out.println("rs1 is not null");

			while (rs1.next()) {

				System.out.println("5 in find function ");

				// String id = rs1.getString("className");
				// String name = rs1.getString("Name");
				className = rs1.getString("ClassName");
				// String inArgType = rs1.getString("InArgType");
				// String outArgType = rs1.getString("OutArgType");
				source = rs1.getString("Source");
				// System.out.println(id + "\t" + name + "\t" + className +
				// "\t" + inArgType + "\t" + outArgType +
				// "\t" + source);
				System.out.println(className + "\t" + source);

				int count = 0;

				String operatorName = "";
				double sumOfCost = 0.0;
				ResultSet rs = ConnectionGTBase.getRelevantGroundTruth(queryOperator);
				while (rs.next()) {
					int id = rs.getInt("idKnowledges");
					operatorName = rs.getString("Operator");
					double inArg1 = Double.parseDouble(rs.getString("Operand1"));
					double inArg2 = Double.parseDouble(rs.getString("Operand2"));
					double outArg = Double.parseDouble(rs.getString("Output"));
					System.out.println(id + "\t" + className + "\t" + inArg1 + "\t" + inArg2 + "\t" + outArg);
					count++;
					/*
					 * ÌïòÎÇòÏùò ÌõÑÎ≥¥ ÏïåÍ≥†Î¶¨Ï¶òÏóê ÎåÄÌïòÏó¨ Ìï¥Îãπ
					 * ÏßÄÏãùÏù¥ ÏñºÎßàÎÇò ÎßûÎäîÏßÄÎ•º ÌôïÏù∏ÌïúÎã§
					 */
					sumOfCost += measureHowDiffAlgorithmIsFromConstraint(className, inArg1, inArg2, outArg, source);
					System.out.println("sumOfCost: " + sumOfCost);

				}
				double cost = (sumOfCost / count);
				Node algoNode = new Node();
				algoNode.cost = cost;
				algoNode.name = className;
				algoNode.source = source;
				System.out.println("name: " + className + "\tcost: " + cost + "\tsource: " + source);
				pq.offer(algoNode);
			}
		} catch (SQLException e) {
			System.err.print(e.getMessage() + " ARGH!");
		} catch (Exception e) {
			System.err.print(e.getMessage() + " FUUUUUUUUUU!");
		}
		return pq;

	}

	public static boolean linkOperatorWithAlgorithm(String queryOperator, String algorithmName) {
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/elab?characterEncoding=UTF-8";
		String dbUsername = "librarian";
		String dbPassword = "40211";
		boolean output = false;

		Statement statement = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			statement = connection.createStatement();
			String str = "UPDATE Algorithm SET Name='" + queryOperator + "'  WHERE Algorithm.ClassName = '"
					+ algorithmName + "';";

			statement.executeUpdate(str);

			str = "select * from Algorithm where Name='" + queryOperator + "';";

			ResultSet rs = statement.executeQuery(str);

			System.out.println("query:" + queryOperator);

			String className = "";
			String source = "";
			int count = 0;

			while (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("Name");
				className = rs.getString("ClassName");
				String inArgType = rs.getString("InArgType");
				String outArgType = rs.getString("OutArgType");
				source = rs.getString("Source");
				System.out.println(
						id + "\t" + name + "\t" + className + "\t" + inArgType + "\t" + outArgType + "\t" + source);
				count++;
			}

			// source Î•º Î∞õÏïÑÏôîÏúºÎ©¥ Í∑∏Í≤ÉÏóê ÌîºÏó∞ÏÇ∞ÏûêÎ•º ÎÑ£Ïñ¥ÏÑú
			// Í∞íÏùÑ Íµ¨ÌïúÎã§ .
			// Ï¶â, ÏûÖÎ†•ÏùÄ ÌîºÏó∞ÏÇ∞ÏûêÏù¥Í≥† Í≤∞Í≥ºÍ∞ôÏùÄ Ïó∞ÏÇ∞
			// Í≤∞Í≥ºÏù¥Îã§.
			// sourceÏùòJava ÌååÏã±Ïù¥ ÌïÑÏöîÌïòÎã§.

			// operating()
			System.out.println("here 1");
			// ParseTreeTest aParseTree = new ParseTreeTest();

			System.out.println("here 2");
			// parsetree_test.main();
			// FileInputStream in = new
			// FileInputStream("/Users/sunghee/Documents/sunghee-data/2015programs/workspace-java/rdf-prog/src/InfoExtractWithKE.java");

			System.out.println("here 2-1");
			if (count != 0)
				output = true;
			else
				output = false;
		} catch (SQLException e) {
			System.err.print(e.getMessage() + " ARGH!");
		} catch (Exception e) {
			System.err.print(e.getMessage() + " FUUUUUUUUUU!");
		}

		return output;
	}

	/* calculate the accuracy of each algorithm against ground truth. */

	public boolean evaluate(String queryOperator, double a1, double a2) {
		// ConnectionGTBase kbase = new ConnectionGTBase();
		ResultSet rs = null;

		// Apparently speaking, the term knowledge should be replaced with
		// ground truth or training set
		// by measurement method.
		// see if there is ground truth related to the query operator
		if (ConnectionGTBase.existRelevantGroundTruth(queryOperator) == true) {
			rs = ConnectionGTBase.getRelevantGroundTruth(queryOperator);
		}

		if (rs == null) {
			System.out.println("rs: is null on existRelevantKnowledge in evaluate...");
			return false;
		} else {
			System.out.println("rs is not null on existRelevantKnowledge in evaluate");
		}

		/* get candidate algorithms related to the operator */

		ResultSet candidateAlgorithmSet = getCandidateAlgorithms(queryOperator);
		if (candidateAlgorithmSet == null) {
			System.out.println("candidateAlgorithmSet: is null on getCandidateAlgorithms in evaluate");
			return false;
		} else {
			System.out.println("candidateAlgorithmSet is not null on getCandidateAlgorithms in evaluate");
		}
		/*
		 * ÌõÑÎ≥¥ÏßÄÏãù ÌïòÎÇòÌïòÎÇòÏóê Í∞ÄÏ†∏Ïò® ÏßÄÏãù(Ï†ïÎãµ)ÏùÑ ÎåÄÏûÖÌï¥ÏÑú
		 * Ï†ÅÏ†àÌïú ÏïåÍ≥†Î¶¨Ï¶òÏùÑ ÌÉêÏÉâÌïúÎã§
		 */
		System.out.println("getEvaluationResult ...");
		PriorityQueue<Node> pqforCandidateAlgo = getEvaluationResult(queryOperator, candidateAlgorithmSet);
		System.out.println("getEvaluationResult complete");

		/*
		 * Í∞ÄÏû• Ï†ÅÏ†àÌïú ÏïåÍ≥†Î¶¨Ï¶òÏùÑ ÏßÄÏãùÏùò Ïó∞ÏÇ∞ÏûêÏôÄ
		 * Ïó∞Í≤∞ÏãúÌÇ®Îã§.
		 */
		System.out.println("linking operator with algorithm ...");
		System.out.println("queryOperator: " + queryOperator);
		System.out.println("pq.peek().name: " + pqforCandidateAlgo);
		while (pqforCandidateAlgo.peek().cost == 0) { // ÎπÑÏö©Ïù¥ 0Ïù¥ÎùºÎäî
														// Ï°∞Í±¥Îßå
			// Í∞ÄÏßÄÍ≥† ÌåêÎã®ÌïòÎäî Í≤ÉÏùÄ ÎÑàÎ¨¥
			// Îã®ÏàúÌï®. ÎÇòÏ§ëÏóê Í≥†Ïπ† ÌïÑÏöî
			// ÏûàÏùå.

			System.out.println("pq. " + pqforCandidateAlgo);
			linkOperatorWithAlgorithm(queryOperator, pqforCandidateAlgo.peek().name);
			pqforCandidateAlgo.poll();
			System.out.println("1.pq.size(): " + pqforCandidateAlgo.size());
			System.out.println("operator has been linked with algorithm complete");
			// return true;
		}
		System.out.println("2.pq.size(): " + pqforCandidateAlgo.size());

		if (pqforCandidateAlgo.peek().cost != 0) {
			return false;
		} else {
			return true;
		}
	}

	public Connection getConnection() {
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/elab?characterEncoding=UTF-8";
		String dbUsername = "librarian";
		String dbPassword = "40211";
		Statement statement = null;
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public ResultSet getResultSet(String queryOperator) {
		Connection connection = getConnection();
		ResultSet rs = null;
		try {
			Statement statement = connection.createStatement();
			String str = "select * from Algorithm where Name='" + queryOperator + "';";
			rs = statement.executeQuery(str);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public String execute(String queryOperator, Stack<String> outputStack) {
		String className = "";
		String source = "";
		String inArgType = "";
		int numInArgs = 0;
		String methodName = "";
		Parameter parameter = new Parameter();
		String result = "";
		URLClassLoader urlClassLoader = null;
		ResultSet rs = getResultSet(queryOperator);
		try {
			while (rs.next()) {
				inArgType = rs.getString("InArgType");
				numInArgs = rs.getInt("NumInArg");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String type[] = inArgType.split(",");
		// type.length -> outputStack.size()+1
		String[] paraAr = new String[type.length];
		
		if (queryOperator.equals("infoExtract")) {
			Ref ref = new Ref();
			ref.setText(outputStack.pop());
//			paraAr[i] = ref; 
		}else{
			
			for (int i = 0; i < paraAr.length; i++) {
				paraAr[i] = outputStack.pop();
			}
		}
		
		parameter.partypes = new Class[numInArgs];
		parameter.parObj = new Object[numInArgs];
		Object callParameter = null; // 메소드를 호출할 때 전달한 인자
		for (int i = 0; i < type.length; i++) {
			// String일 경우에만 되어있지만 여러개를 case문으로 돌릴 예정
			// String 외에도 int Integer double 등등
			if (type[i].equals("java.lang.String")) {
				Object parObj;
				try {
					parameter.setParTypes(Class.forName("java.lang.String"), i);
					callParameter = paraAr[i];
					parameter.setParObj(callParameter, i);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				rs = getResultSet(type[i]);
				System.out.println("query: " + type[i]);
				try {
					while (rs.next()) {
						String id = rs.getString("ID");
						String name = rs.getString("Name");
						className = rs.getString("ClassName");
						inArgType = rs.getString("InArgType");
						numInArgs = rs.getInt("NumInArg");
						String outArgType = rs.getString("OutArgType");
						source = rs.getString("Source");
						methodName = rs.getString("MethodName");
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

				String path = ConnectionAlgoDB.class.getResource("").getPath();

				ReflectionMaker rMakerP = new ReflectionMaker(outputStack);
				File file = rMakerP.writeSource(className, source, path); // 소스파일
				// className.java로 작성
				if (rMakerP.compileSource(file)) {
					try {
						urlClassLoader = rMakerP.sourceClassLoader(path);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					// 파라미터가 객체일 시 파라미터를 만든다.
					parameter = rMakerP.makeParObject(urlClassLoader, className, methodName, numInArgs, inArgType,
							parameter, i);
				}
			}
		}
		try {
			rs = getResultSet(queryOperator);
			System.out.println("query: " + queryOperator);
			while (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("Name");
				className = rs.getString("ClassName");
				inArgType = rs.getString("InArgType");
				numInArgs = rs.getInt("NumInArg");
				String outArgType = rs.getString("OutArgType");
				source = rs.getString("Source");
				methodName = rs.getString("MethodName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String path = ConnectionAlgoDB.class.getResource("").getPath();
		ReflectionMaker rMaker = new ReflectionMaker(outputStack);
		File file = rMaker.writeSource(className, source, path); // 소스파일
		try {
			boolean compileCheck = false;
			boolean flag = true;
			if (rMaker.compileSource(file)) {
				urlClassLoader = rMaker.sourceClassLoader(path);
				System.out.println("소스런");
				result = rMaker.sourceRun(urlClassLoader, className, inArgType, numInArgs, methodName, parameter);
//				hValue = rMaker.sourceRun(urlClassLoader, className, inArgType, numInArgs, "evaluate", parameter);
				System.out.println("소스런끝");
			} else {
				System.out.println("컴파일 안됨");
			}
			urlClassLoader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String execute(String queryOperator, Stack<String> outputStack, String parentValue) {
		String className = "";
		String source = "";
		String inArgType = "";
		int numInArgs = 0;
		String methodName = "";
		Parameter parameter = new Parameter();
		String result = "";
		// String[] a = new String[outputStack.size()];
		URLClassLoader urlClassLoader = null;
		// for (int i = 0; i < a.length; i++) {
		// a[i] = (String) outputStack.pop();
		// }
		// String output = (String) outputStack.pop();
		ResultSet rs = getResultSet(queryOperator);
		try {
			while (rs.next()) {
				inArgType = rs.getString("InArgType");
				numInArgs = rs.getInt("NumInArg");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String type[] = inArgType.split(",");
		// type.length -> outputStack.size()+1
		String[] paraAr = new String[type.length];
		paraAr[0] = parentValue;
		for (int i = 1; i < paraAr.length; i++) {
			if(i==2){
				outputStack.push("infoExtract");
			}
				paraAr[i] = outputStack.pop();
		}
		parameter.partypes = new Class[numInArgs];
		parameter.parObj = new Object[numInArgs];
		Object callParameter = null; // 메소드를 호출할 때 전달한 인자
		for (int i = 0; i < type.length; i++) {
			// String일 경우에만 되어있지만 여러개를 case문으로 돌릴 예정
			// String 외에도 int Integer double 등등
			if (type[i].equals("java.lang.String")) {
				Object parObj;
				try {
					parameter.setParTypes(Class.forName("java.lang.String"), i);
					callParameter = paraAr[i];
					parameter.setParObj(callParameter, i);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				rs = getResultSet(type[i]);
				System.out.println("query: " + type[i]);
				try {
					while (rs.next()) {
						String id = rs.getString("ID");
						String name = rs.getString("Name");
						className = rs.getString("ClassName");
						inArgType = rs.getString("InArgType");
						numInArgs = rs.getInt("NumInArg");
						String outArgType = rs.getString("OutArgType");
						source = rs.getString("Source");
						methodName = rs.getString("MethodName");
						// System.out.println(name + "\t" + inArgType + "\t" +
						// outArgType + "\t");
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

				String path = ConnectionAlgoDB.class.getResource("").getPath();

				ReflectionMaker rMakerP = new ReflectionMaker(outputStack);
				File file = rMakerP.writeSource(className, source, path); // 소스파일
				// className.java로
				// 작성

				if (rMakerP.compileSource(file)) {
					try {
						urlClassLoader = rMakerP.sourceClassLoader(path);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}

					// 파라미터가 객체일 시 파라미터를 만든다.
					parameter = rMakerP.makeParObject(urlClassLoader, className, methodName, numInArgs, inArgType,
							parameter, i);
					// ParameterAr.add(rMakerP.makeParObject(urlClassLoader,
					// className, methodName, numInArgs ,inArgType));
					// rMakerP.sourceRun(urlClassLoader, className, inArgType,
					// numInArgs, methodName, parObjectAr);
				}
			}

		}

		try {
			rs = getResultSet(queryOperator);
			System.out.println("query: " + queryOperator);
			// for (int i = 0; i < a.length; i++) {
			// System.out.println(a[i]);
			// }
			while (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("Name");
				className = rs.getString("ClassName");
				inArgType = rs.getString("InArgType");
				numInArgs = rs.getInt("NumInArg");
				String outArgType = rs.getString("OutArgType");
				source = rs.getString("Source");
				methodName = rs.getString("MethodName");
				// System.out.println(name + "\t" + inArgType + "\t" +
				// outArgType + "\t");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String path = ConnectionAlgoDB.class.getResource("").getPath();

		// System.out.println(path);

		ReflectionMaker rMaker = new ReflectionMaker(outputStack);
		File file = rMaker.writeSource(className, source, path); // 소스파일
		// className.java로
		// 작성
		// URLClassLoader urlClassLoader = null;

		try {
			boolean compileCheck = false;
			boolean flag = true;
			// Stack<String> stack = new Stack<String>();
			// stack.push("String");
			if (rMaker.compileSource(file)) {
				urlClassLoader = rMaker.sourceClassLoader(path);
				System.out.println("소스런");
				result = rMaker.sourceRun(urlClassLoader, className, inArgType, numInArgs, methodName, parameter);
				System.out.println("소스런끝");

				// rMaker.sourceRun2(urlClassLoader, className, inArgType,
				// numInArgs, methodName, stack);
			} else {
				System.out.println("컴파일 안됨");
			}

			// compileCheck = rMaker.compileSource(file);
			// if (compileCheck) {// 소스파일 컴파일
			// System.out.println("컴파일됨");
			// urlClassLoader = rMaker.sourceClassLoader(path);
			// if (flag) {
			// parObject = rMaker.makeParObject(urlClassLoader, className,
			// methodName, numInArgs);
			// flag = false;
			// } else {
			// rMaker.sourceRun(urlClassLoader, className, inArgType, numInArgs,
			// methodName, parObject);
			// }
			// } else {
			// flag = false;
			// System.out.println("컴파일 안됨");
			//
			// }
			urlClassLoader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}

	// public Stack<String> execute2(String queryOperator, Stack<String>
	// outputStack, String parentValue) {
	// String className = "";
	// String source = "";
	// String inArgType = "";
	// int numInArgs = 0;
	// String methodName = "";
	// ParameterAr = new ArrayList<>();
	// ResultSet rs = getResultSet(queryOperator);
	// try {
	// while (rs.next()) {
	// inArgType = rs.getString("InArgType");
	// }
	// } catch (SQLException e1) {
	// e1.printStackTrace();
	// }
	//
	// if (inArgType!=null) {
	// String type[] = inArgType.split(",");
	// for (int i = 0; i < type.length; i++) {
	// rs = getResultSet(type[i]);
	// System.out.println("query: " + type[i]);
	// try {
	// while (rs.next()) {
	// String id = rs.getString("ID");
	// String name = rs.getString("Name");
	// className = rs.getString("ClassName");
	// inArgType = rs.getString("InArgType");
	// numInArgs = rs.getInt("NumInArg");
	// String outArgType = rs.getString("OutArgType");
	// source = rs.getString("Source");
	// methodName = rs.getString("MethodName");
	// System.out.println(name + "\t" + inArgType + "\t" + outArgType + "\t");
	// }
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// String path = ConnectionAlgoDB.class.getResource("").getPath();
	// System.out.println(path);
	//
	// // ReflectionMaker rMakerP = new ReflectionMaker(inputStack,
	// // outputStack);
	// ReflectionMaker rMakerP = new ReflectionMaker();
	// File file = rMakerP.writeSource(className, source, path); // 소스파일
	// // className.java로
	// // 작성
	// URLClassLoader urlClassLoader = null;
	//
	// if (rMakerP.compileSource(file)) {
	// try {
	// urlClassLoader = rMakerP.sourceClassLoader(path);
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// }
	// ParameterAr.add(rMakerP.makeParObject(urlClassLoader, className,
	// methodName, numInArgs, inArgType));
	// // rMakerP.sourceRun(urlClassLoader, className, inArgType,
	// // numInArgs, methodName, parObjectAr);
	// }
	//
	// }
	// }
	// try {
	// rs = getResultSet(queryOperator);
	// System.out.println("query: " + queryOperator);
	// while (rs.next()) {
	// String id = rs.getString("ID");
	// String name = rs.getString("Name");
	// className = rs.getString("ClassName");
	// inArgType = rs.getString("InArgType");
	// numInArgs = rs.getInt("NumInArg");
	// String outArgType = rs.getString("OutArgType");
	// source = rs.getString("Source");
	// methodName = rs.getString("MethodName");
	// System.out.println(name + "\t" + inArgType + "\t" + outArgType + "\t");
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// String path = ConnectionAlgoDB.class.getResource("").getPath();
	//// String path =
	// "/C:/Users/Hooo/workspace/InfoExtract_Test/bin/com/HooMin/";
	// System.out.println(path);
	//
	// // ReflectionMaker rMaker = new ReflectionMaker(inputStack,
	// // outputStack);
	// ReflectionMaker rMaker = new ReflectionMaker();
	// File file = rMaker.writeSource(className, source, path); // 소스파일
	// // className.java로
	// // 작성
	// URLClassLoader urlClassLoader = null;
	//
	// try {
	// boolean compileCheck = false;
	// boolean flag = true;
	//
	// if (rMaker.compileSource(file)) {
	// urlClassLoader = rMaker.sourceClassLoader(path);
	//// rMaker.sourceRun(urlClassLoader, className, inArgType, numInArgs,
	// methodName, ParameterAr);
	// outputStack.push((String) rMaker.sourceRun2(urlClassLoader, className,
	// inArgType, numInArgs, methodName, outputStack, parentValue));
	// }
	//
	// // compileCheck = rMaker.compileSource(file);
	// // if (compileCheck) {// 소스파일 컴파일
	// // System.out.println("컴파일됨");
	// // urlClassLoader = rMaker.sourceClassLoader(path);
	// // if (flag) {
	// // parObject = rMaker.makeParObject(urlClassLoader, className,
	// // methodName, numInArgs);
	// // flag = false;
	// // } else {
	// // rMaker.sourceRun(urlClassLoader, className, inArgType, numInArgs,
	// // methodName, parObject);
	// // }
	// // } else {
	// // flag = false;
	// // System.out.println("컴파일 안됨");
	// //
	// // }
	// urlClassLoader.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return outputStack;
	//
	// }

	// public static interface DoStuff {
	// public void doStuff();
	// }
	public static interface DoStuff {
		public void doStuff();
	}

	public static void main(String[] args) {
		ConnectionAlgoDB connectionAlgoDB = new ConnectionAlgoDB();
		Stack<String> inputStack = new Stack<>();
		Stack<String> outputStack = new Stack<>();

		// outputStack.push("박성희, (2016), \"KE\", 정보관리학회, 33, (3), pp. 22-40 ");
		connectionAlgoDB.execute("infoExtract", outputStack, "test");
		// connectionAlgoDB.execute("typeOf", inputStack, outputStack);
		// connectionAlgoDB.execute("Ref", inputStack, outputStack);
		// connectionAlgoDB.execute("infoExtract", outputStack, outputStack);
	}
}
