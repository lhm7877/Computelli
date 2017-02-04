package addition;

import java.sql.*;
import java.io.*;
import javax.tools.*;
import java.util.*;
import java.net.*;
import java.lang.reflect.*;
import java.io.FileInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import japa.parser.ast.CompilationUnit;
import japa.parser.JavaParser;
import addition.ParseTreeTest;

public class ConnectionInverse {

	public ConnectionInverse() {

	}

	public boolean existRelevantAlgorithm(String queryOperator, double a1,
			double a2) {
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/elab?characterEncoding=UTF-8";
		String dbUsername = "librarian";
		String dbPassword = "40211";

		boolean output = false;

		Statement statement = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl,
					dbUsername, dbPassword);
			statement = connection.createStatement();

			String str = "select * from Algorithms where Name='"
					+ queryOperator + "';";
			rs = statement.executeQuery(str);

			System.out.println("query:" + queryOperator);
			System.out.println("a1:" + a1);
			System.out.println("a2:" + a2);

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
				System.out.println(id + "\t" + name + "\t" + className + "\t"
						+ inArgType + "\t" + outArgType + "\t" + source);
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

	public ResultSet getRelevantAlgorithm(String queryOperator, double a1,
			double a2) {
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/elab?characterEncoding=UTF-8";
		String dbUsername = "librarian";
		String dbPassword = "40211";
		Double output = null;

		Statement statement = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl,
					dbUsername, dbPassword);
			statement = connection.createStatement();
			String str = "select count(*) from Algorithms where Name='"
					+ queryOperator + "';";

			rs = statement.executeQuery(str);
			System.out.println("rs: " + rs);

			str = "select * from Algorithms where Name='" + queryOperator
					+ "';";
			rs = statement.executeQuery(str);

			System.out.println("query:" + queryOperator);
			System.out.println("a1:" + a1);
			System.out.println("a2:" + a2);

			String className = "";
			String source = "";
			int count = 0;

			/*
			 * while (rs.next()) { String id = rs.getString("ID"); String name =
			 * rs.getString("Name"); className = rs.getString("ClassName");
			 * String inArgType = rs.getString("InArgType"); String outArgType =
			 * rs.getString("OutArgType"); source = rs.getString("Source");
			 * System.out.println(id + "\t" + name + "\t" + className + "\t" +
			 * inArgType + "\t" + outArgType + "\t" + source); count++; }
			 */
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
		Double output = null;

		Statement statement = null;
		ResultSet rs = null, tempRS = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl,
					dbUsername, dbPassword);
			statement = connection.createStatement();
			// String str =
			// "select * from Algorithms where Name='"+queryOperator+"';";

			String str = "select B.className, B.Source "
					+ " from elab.Ontology as A , elab.Algorithm as B "
					+ "where (select A.name where (A.BT in (SELECT A.Name  FROM elab.Ontology as A where A.NT ='"
					+ queryOperator + "'))) = B.className;";

			rs = statement.executeQuery(str);
			tempRS = rs;

			System.out.println("query:" + queryOperator);

			String className = "";
			String source = "";
			int count = 0;

			/*
			 * 
			 * while (rs.next()) { //String id = rs.getString("className");
			 * //String name = rs.getString("Name"); className =
			 * rs.getString("ClassName"); //String inArgType =
			 * rs.getString("InArgType"); //String outArgType =
			 * rs.getString("OutArgType"); source = rs.getString("Source");
			 * //System.out.println(id + "\t" + name + "\t" + className + //
			 * "\t" + inArgType + "\t" + outArgType + // "\t" + source);
			 * System.out.println(className + "\t" + source ); count++; } if
			 * (count!=0) {return tempRS;} else return null;
			 */
		} catch (SQLException e) {
			System.err.print(e.getMessage() + " ARGH!");
		} catch (Exception e) {
			System.err.print(e.getMessage() + " FUUUUUUUUUU!");
		}
		return tempRS;

	}

	private static double calculateCostOfAlgorithm(String className,
			double inArg1, double inArg2, double outArg, String source) {
		double output = 0.0;
		File helloWorldJava = new File("./" + className + ".java");// ("./add.java");
		System.out.println("here 5-2");
		if (helloWorldJava.getParentFile().exists()
				|| helloWorldJava.getParentFile().mkdirs()) {

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

				/** Compilation Requirements *********************************************************************************************/
				DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
				JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
				StandardJavaFileManager fileManager = compiler
						.getStandardFileManager(diagnostics, null, null);

				// This sets up the class path that the compiler will use.
				// I've added the .jar file that contains the DoStuff interface
				// within in it...
				List<String> optionList = new ArrayList<String>();
				optionList.add("-classpath");
				optionList.add(System.getProperty("java.class.path")
						+ ";dist/InlineCompiler.jar");

				Iterable<? extends JavaFileObject> compilationUnit = fileManager
						.getJavaFileObjectsFromFiles(Arrays
								.asList(helloWorldJava));
				JavaCompiler.CompilationTask task = compiler.getTask(null,
						fileManager, diagnostics, optionList, null,
						compilationUnit);
				/********************************************************************************************* Compilation Requirements **/
				if (task.call()) {
					/** Load and execute *************************************************************************************************/
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
					System.out.println("Yipe4");
					// Santity check
					if (obj instanceof DoStuff) {
						// Cast to the DoStuff interface
						DoStuff stuffToDo = (DoStuff) obj;
						// Run it baby
						stuffToDo.doStuff();
					}
					/************************************************************************************************* Load and execute **/
				} else {
					System.out.println("Error");
					for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics
							.getDiagnostics()) {
						System.out.format("Error on line %d in %s%n",
								diagnostic.getLineNumber(), diagnostic
										.getSource().toUri());
					}
				}
				fileManager.close();
				System.out.println("here is 6");
			} catch (IOException | ClassNotFoundException
					| InstantiationException | IllegalAccessException exp) {
				exp.printStackTrace();
			}
		}
		String strString = "java.lang.Double";// "java.lang.String";
		String strClass = className; // "addition";//"tutorial.HTSCRE";
		String strMethod = className;// "add";//getNodes(infmodel, a ,p);
		try {
			System.out.println("here is 7");
			Class<?> passedClass = Class.forName(strClass); // tutorial.HTSCRE

			// parameter types for methods
			Class<?>[] partypes = new Class[] { Class.forName(strString),
					Class.forName(strString) }; // input type
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
			System.out.println("here is 11");
			System.out.println(arglist[0] + " " + className + " " + arglist[1]
					+ " = " + output);

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
		}
		return Math.abs((double) (output - outArg));

	}

	static class Node {
		int id; // node id
		double cost; // error cost with respect to our knowledges
		String name;// node name
		String source;
	}

	static class PriorityQueueAlgorithm implements Comparator<Node> {
		public int compare(Node one, Node two) {
			return (int) (one.cost) - (int) (two.cost);
		}
	}

	public static PriorityQueue<Node> findShortestPath(String queryOperator,
			ResultSet rs1) {
		/* rs1 : algorithms */
		
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/elab?characterEncoding=UTF-8";
		String dbUsername = "librarian";
		String dbPassword = "40211";
		boolean output = false;

		Statement statement = null;
		String className = "";
		String source = "";

		/* returns the node which has a minimal cost. 
		 * implements A* algorithm 
		 */
				
		/* create a priority queue class defining a cost function to compare arbitrary two nodes 
		 */
		PriorityQueueAlgorithm pqa = new PriorityQueueAlgorithm();

		
		 /* create a priority queue for nodes
		  */
		PriorityQueue<Node> pq = new PriorityQueue<Node>(10, pqa);

		try {

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
				
				/* get relevant knowledgeable statements  related to 
				 * the query operator that we want to find its source code  
				 */
				
				ResultSet rs = ConnectionGTBase
						.getRelevantGroundTruth(queryOperator);
				while (rs.next()) {
					int id = rs.getInt("idKnowledges");
					operatorName = rs.getString("Operator");
					double inArg1 = Double
							.parseDouble(rs.getString("Operand1"));
					double inArg2 = Double
							.parseDouble(rs.getString("Operand2"));
					double outArg = Double.parseDouble(rs.getString("Output"));
					System.out.println(id + "\t" + className + "\t" + inArg1
							+ "\t" + inArg2 + "\t" + outArg);
					count++;
					/*
					 * calculate the cost of how the algorithm is complied on knowledges related to it. 
					 */
					
					sumOfCost += calculateCostOfAlgorithm(className, inArg1,
							inArg2, outArg, source);
					System.out.println("sumOfCost: " + sumOfCost);

				}
				
				double cost = (sumOfCost / count);
				Node algoNode = new Node();
				algoNode.cost = cost;
				algoNode.name = className;
				algoNode.source = source;
				System.out.println("name: " + className + "\tcost: " + cost
						+ "\tsource: " + source);
				pq.offer(algoNode);
				
			}
			
		} catch (SQLException e) {
			System.err.print(e.getMessage() + " ARGH!");

		} catch (Exception e) {
			System.err.print(e.getMessage() + " FUUUUUUUUUU!");

		}
		return pq;

	}

	public static boolean linkOperatorWithAlgorithm(String queryOperator,
			String algorithmName) {
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/elab?characterEncoding=UTF-8";
		String dbUsername = "librarian";
		String dbPassword = "40211";
		boolean output = false;

		Statement statement = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl,
					dbUsername, dbPassword);
			statement = connection.createStatement();
			String str = "UPDATE Algorithms SET Name='" + queryOperator
					+ "'  WHERE Algorithms.ClassName = '" + algorithmName
					+ "';";

			statement.executeUpdate(str);

			str = "select * from Algorithms where Name='" + queryOperator
					+ "';";

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
				System.out.println(id + "\t" + name + "\t" + className + "\t"
						+ inArgType + "\t" + outArgType + "\t" + source);
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

	public double evaluate(String queryOperator, double a1, double a2) {
		double a3 = 0.0;
		/* 
		 * first, get the query-related knowledge from the kbase 
		 */
		ConnectionGTBase kbase = new ConnectionGTBase();
		ResultSet rs = null;

		if (ConnectionGTBase.existRelevantGroundTruth(queryOperator) == true) {
			// rs is query-related knowledge, e.g., plus 3, 2, 5; plus -1, -2, -3 
			rs = ConnectionGTBase.getRelevantGroundTruth(queryOperator); 
		}

		if (rs == null)
			System.out.println("rs: is null");
		else
			System.out.println("rs is not null");
		/* Í∞ÄÏ†∏Ïò® ÏßÄÏãùÍ≥º ÎßûÏùÄ ÌõÑÎ≥¥ ÏïåÍ≥†Î¶¨Ï¶òÏùÑ Î™®Îëê Í∞ÄÏ†∏Ïò®Îã§ */

		ResultSet rs2 = getCandidateAlgorithms(queryOperator);
		if (rs2 == null)
			System.out.println("rs2: is null");
		else
			System.out.println("rs2 is not null");

		/*
		 * ÌõÑÎ≥¥ÏßÄÏãù ÌïòÎÇòÌïòÎÇòÏóê Í∞ÄÏ†∏Ïò® ÏßÄÏãù(Ï†ïÎãµ)ÏùÑ ÎåÄÏûÖÌï¥ÏÑú
		 * Ï†ÅÏ†àÌïú ÏïåÍ≥†Î¶¨Ï¶òÏùÑ ÌÉêÏÉâÌïúÎã§
		 */
		System.out.println("findShortestPath ...");
		PriorityQueue<Node> pq = findShortestPath(queryOperator, rs2); // rs2 are candidate algorithm to be evaluated
		System.out.println("findShortestPath complete");

		/*
		 * Í∞ÄÏû• Ï†ÅÏ†àÌïú ÏïåÍ≥†Î¶¨Ï¶òÏùÑ ÏßÄÏãùÏùò Ïó∞ÏÇ∞ÏûêÏôÄ
		 * Ïó∞Í≤∞ÏãúÌÇ®Îã§.
		 */
		System.out.println("link operator with algorithm ...");
		System.out.println("queryOperator: " + queryOperator);
		System.out.println("pq.peek().name: " + pq);
		while (pq.peek().cost == 0) { // ÎπÑÏö©Ïù¥ 0Ïù¥ÎùºÎäî Ï°∞Í±¥Îßå
										// Í∞ÄÏßÄÍ≥† ÌåêÎã®ÌïòÎäî Í≤ÉÏùÄ ÎÑàÎ¨¥
										// Îã®ÏàúÌï®. ÎÇòÏ§ëÏóê Í≥†Ïπ† ÌïÑÏöî
										// ÏûàÏùå.
			linkOperatorWithAlgorithm(queryOperator, pq.peek().name);
			pq.poll();
			System.out.println("pq.size(): " + pq.size());
		}
		System.out.println("link operator with algorithm complete");
		return a3;
	}

	public double connect(String unknown, String queryOperator, double a1,
			double a2) {

		String dbUrl = "jdbc:mysql://127.0.0.1:3306/elab?characterEncoding=UTF-8";
		String dbUsername = "librarian";
		String dbPassword = "40211";
		Double output = null;

		Statement statement = null;

		// 알고리즘 테이블에 접속 queryOperator에 해당하는 알고리즘을 가져온다.
		// 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl,
					dbUsername, dbPassword);
			statement = connection.createStatement();
			String str = "select * from Algorithm where Name='"
					+ queryOperator + "';";

			ResultSet rs = statement.executeQuery(str);

			System.out.println("query:" + queryOperator);
			System.out.println("a1:" + (double)a1);
			System.out.println("a2:" + a2);

			String className = "";
			String source = "";

			while (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("Name");
				className = rs.getString("ClassName");
				String inArgType = rs.getString("InArgType");
				String outArgType = rs.getString("OutArgType");
				source = rs.getString("Source");
				System.out.println(id + "\t" + name + "\t" + className + "\t"
						+ inArgType + "\t" + outArgType + "\t" + source);
			}

			connection.close();
			// source Î•º Î∞õÏïÑÏôîÏúºÎ©¥ Í∑∏Í≤ÉÏóê ÌîºÏó∞ÏÇ∞ÏûêÎ•º ÎÑ£Ïñ¥ÏÑú
			// Í∞íÏùÑ Íµ¨ÌïúÎã§.
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
			// FileInputStream in = new FileInputStream(source);
			// convert String into InputStream
			InputStream in = new ByteArrayInputStream(source.getBytes());

			System.out.println("here 2-2");
			CompilationUnit cu;
			System.out.println("here 3");
			try {
				// parse the file
				cu = JavaParser.parse(in);
			} finally {
				in.close();
			}

			System.out.println("here 4");
			new ParseTreeTest.MethodVisitor().visit(cu, null);
			System.out.println("here 5");
			System.out.println(cu.toString());
			System.out.println("here 5-1");

			File helloWorldJava = new File("./bin/" + className + ".java");// ("./add.java");
			System.out.println("here 5-2");
			if (helloWorldJava.getParentFile().exists()
					|| helloWorldJava.getParentFile().mkdirs()) {

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

					/** Compilation Requirements *********************************************************************************************/
					DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
					JavaCompiler compiler = ToolProvider
							.getSystemJavaCompiler();
					StandardJavaFileManager fileManager = compiler
							.getStandardFileManager(diagnostics, null, null);

					// This sets up the class path that the compiler will use.
					// I've added the .jar file that contains the DoStuff
					// interface within in it...
					List<String> optionList = new ArrayList<String>();
					optionList.add("-classpath");
					optionList.add(System.getProperty("java.class.path")
							+ ";dist/InlineCompiler.jar");

					Iterable<? extends JavaFileObject> compilationUnit = fileManager
							.getJavaFileObjectsFromFiles(Arrays
									.asList(helloWorldJava));
					JavaCompiler.CompilationTask task = compiler.getTask(null,
							fileManager, diagnostics, optionList, null,
							compilationUnit);
					/********************************************************************************************* Compilation Requirements **/
					if (task.call()) {
						/** Load and execute *************************************************************************************************/
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
						System.out.println("Yipe4");
						// Santity check
						if (obj instanceof DoStuff) {
							// Cast to the DoStuff interface
							DoStuff stuffToDo = (DoStuff) obj;
							// Run it baby
							stuffToDo.doStuff();
						}
						/************************************************************************************************* Load and execute **/
					} else {
						System.out.println("Error");
						for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics
								.getDiagnostics()) {
							System.out.format("Error on line %d in %s%n",
									diagnostic.getLineNumber(), diagnostic
											.getSource().toUri());
						}
					}
					fileManager.close();
					System.out.println("here is 6");
				} catch (IOException | ClassNotFoundException
						| InstantiationException | IllegalAccessException exp) {
					exp.printStackTrace();
				}
			}
			String strString = "java.lang.Double";// "java.lang.String";
			String strClass = className; // "addition";//"tutorial.HTSCRE";
			String strMethod = className;// "add";//getNodes(infmodel, a ,p);
			try {
				System.out.println("here is 7");
				Class<?> passedClass = Class.forName(strClass); // tutorial.HTSCRE

				// parameter types for methods
				Class<?>[] partypes = new Class[] { Class.forName(strString),
						Class.forName(strString) }; // input type
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
				Constructor<?> constr = passedClass
						.getConstructor(constrpartypes); // class constructor
				// create instance
				Object dummyto = constr.newInstance(); // create class instance

				// Arguments to be passed into method
				Object[] arglist = new Object[] { (a1), (a2)};
				// invoke method!!
				System.out.println("here is 10");
				output = (Double) meth.invoke(dummyto, arglist); // method,
																	// class
																	// instance
				System.out.println("here is 11");
				System.out.println(arglist[0] + " " + queryOperator + " "
						+ arglist[1] + " = " + output);

				System.out.println("here is 12");
				return (double) (output);

				// ÎßåÏïΩ Ïô∏Î∂ÄÏßÄÏãù Í∞ÄÏö¥Îç∞ subclassÍ∞Ä ÏûàÎã§Î©¥
				// ÏûÖÎ†•ÏûêÎ£åÎ•º ÌôïÏù∏ÌïòÏó¨ classificationÏùÑ ÏàòÌñâÌïòÎùº.
				/*
				 * Resource res = someModel.createResource(eg+"Ref"); Resource
				 * exRes = someModel.createResource(eg+"IEEEStyledRef");
				 * 
				 * // Property pro = someModel.getProperty(RDFS.subClassOf);
				 * System.out.println(res.toString());
				 * System.out.println(exRes.toString());
				 * 
				 * Statement subclassstmt =
				 * someModel.createStatement(res,RDFS.subClassOf,exRes);
				 * System.out.println(subclassstmt.toString()); if
				 * (someModel.containsResource(RDFS.subClassOf)) { Resource res2
				 * = someModel.getResource(eg+"InfoExtract");
				 * System.out.println("do contain");
				 * 
				 * Classifier cl = new Classifier();
				 * System.out.println(cl.classify(exRes.toString()));
				 * 
				 * 
				 * } else { System.out.println("does not contain"); }
				 */

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
			}

		} catch (SQLException e) {
			System.err.print(e.getMessage() + " ARGH!");
		} catch (Exception e) {
			System.err.print(e.getMessage() + " FUUUUUUUUUU!");
		}
		return (double) (output);
	}

	public static interface DoStuff {

		public void doStuff();
	}

}
