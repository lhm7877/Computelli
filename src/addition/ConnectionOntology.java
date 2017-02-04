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

public class ConnectionOntology {
	
	PriorityQueue<Node> pqforCandidateAlgo;

	public ConnectionOntology() {

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
			Connection connection = DriverManager.getConnection(dbUrl,
					dbUsername, dbPassword);
			statement = connection.createStatement();

			String str = "select * from Algorithm where Name='" + queryOperator
					+ "';";
			rs = statement.executeQuery(str);

			System.out.println("query in existRelevantAlgorithm:" + queryOperator);
			

			String className = "";
			String source = "";
			int count = 0;
			
			try {
				rs.last(); count = rs.getRow(); rs.beforeFirst();
			} catch(Exception e) {
				System.err.println(e.getMessage()+"ARGH!!");
			}

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

	public static ResultSet getRelevantAlgorithm(String queryOperator) {
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/elab?characterEncoding=UTF-8";
		String dbUsername = "librarian";
		String dbPassword = "40211";

		Statement statement = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl,
					dbUsername, dbPassword);
			statement = connection.createStatement();
			String str = "select count(*) from Algorithm where Name='"
					+ queryOperator + "';";

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
			}
			catch(Exception ex) {
				System.err.println(ex.getMessage()+"ARGH!!");
			}
			
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
			Connection connection = DriverManager.getConnection(dbUrl,
					dbUsername, dbPassword);
			statement = connection.createStatement();
			// String str =
			// "select * from Algorithm where Name='"+queryOperator+"';";

			String str = "select B.className, B.Source "
					+ " from elab.Ontology as A , elab.Algorithm as B "
					+ "where (select A.name where (A.BT in (SELECT A.Name  FROM elab.Ontology as A where A.NT ='"
					+ queryOperator + "'))) = B.className;";

			rs = statement.executeQuery(str);
			


			String className = "";
			String source = "";
			int count = 0;
			try {
				rs.last(); count = rs.getRow(); rs.beforeFirst();
				
			} catch (Exception e)
			{
				System.err.println(e.getMessage()+"ARGH!!");
			}
			System.out.println("query: " + queryOperator +"\nNumOf Result Set: " + count);


			 while (rs.next()) { //String id = rs.getString("className");
			 //String name = rs.getString("Name"); 
			 className = rs.getString("ClassName"); //String inArgType =
			 //rs.getString("InArgType"); //String outArgType =
			 //rs.getString("OutArgType"); 
			 source = rs.getString("Source");
			 //System.out.println(id + "\t" + name + "\t" + className + 
			 //"\t" + inArgType + "\t" + outArgType + // "\t" + source);
			  System.out.println(className + "\t" + source ); }
			 if (count!=0) {rs.beforeFirst();return rs;} else return null;
			 
		} catch (SQLException e) {
			System.err.print(e.getMessage() + " ARGH!");
		} catch (Exception e) {
			System.err.print(e.getMessage() + " FUUUUUUUUUU!");
		}
		return tempRS;

	}

	private static double measureHowDiffAlgorithmIsFromConstraint(String className,
			double inArg1, double inArg2, double outArg, String source) {
		double output = 0.0;
		File helloWorldJava = new File("./" + className + ".java");// ("./add.java");
		System.out.println("here 5-2 in calculateCostOfAlgorithm");
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
					classLoader.close();
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
			System.out.println("strClass :"+strClass);

			URLClassLoader classLoader = new URLClassLoader(
					new URL[] { new File("./").toURI().toURL() });

			Class<?> passedClass = classLoader.loadClass(strClass);// "add"  tutorial.HTSCRE

			
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
			classLoader.close();
			
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
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
	}
		
		return Math.abs((double) (output - outArg));

	}

	static class Node {
		int id;
		double cost; // error cost with respect to each ground truth set elements
		String name;// node name
		String source;
	}

	static class PriorityQueueAlgorithm implements Comparator<Node> {
		public int compare(Node one, Node two) {
			return (int) (one.cost) - (int) (two.cost);
		}
	}

	public static PriorityQueue<Node> getEvaluationResult(String queryOperator,
			ResultSet rs1) {

		String className = "";
		String source = "";

		/* 가장 최소비용을 가지는 Node를 찾아 리턴한다 */
		/* A* 알고리즘을 구현한다 */
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
					 * 하나의 후보 알고리즘에 대하여 해당
					 * 지식이 얼마나 맞는지를 확인한다
					 */
					sumOfCost += measureHowDiffAlgorithmIsFromConstraint(className, inArg1,
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
			String str = "UPDATE Algorithm SET Name='" + queryOperator
					+ "'  WHERE Algorithm.ClassName = '" + algorithmName + "';";

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
				System.out.println(id + "\t" + name + "\t" + className + "\t"
						+ inArgType + "\t" + outArgType + "\t" + source);
				count++;
			}

			// source 를 받아왔으면 그것에 피연산자를 넣어서
			// 값을 구한다 .
			// 즉, 입력은 피연산자이고 결과같은 연산
			// 결과이다.
			// source의Java 파싱이 필요하다.

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
//		ConnectionGTBase kbase = new ConnectionGTBase();
		ResultSet rs = null;

		// Apparently speaking, the term knowledge should be replaced with ground truth or training set 
		// by measurement method.
		// see if there is ground truth related to the query operator
		if (ConnectionGTBase.existRelevantGroundTruth(queryOperator) == true) {
			rs = ConnectionGTBase.getRelevantGroundTruth(queryOperator);
		}

		if (rs == null)
		{
			System.out.println("rs: is null on existRelevantKnowledge in evaluate...");
			return false;
		}
		else
		{
			System.out.println("rs is not null on existRelevantKnowledge in evaluate");
		}
		
		/* get candidate algorithms related to the operator */

		ResultSet candidateAlgorithmSet = getCandidateAlgorithms(queryOperator);
		if (candidateAlgorithmSet == null) {
			System.out.println("candidateAlgorithmSet: is null on getCandidateAlgorithms in evaluate");
			return false;
		}
		else
		{
			System.out.println("candidateAlgorithmSet is not null on getCandidateAlgorithms in evaluate");
		}
		/*
		 * 후보지식 하나하나에 가져온 지식(정답)을 대입해서
		 * 적절한 알고리즘을 탐색한다
		 */
		System.out.println("getEvaluationResult ...");
		PriorityQueue<Node> pqforCandidateAlgo = getEvaluationResult(queryOperator, candidateAlgorithmSet);
		System.out.println("getEvaluationResult complete");

		/*
		 * 가장 적절한 알고리즘을 지식의 연산자와
		 * 연결시킨다.
		 */
		System.out.println("linking operator with algorithm ...");
		System.out.println("queryOperator: " + queryOperator);
		System.out.println("pq.peek().name: " + pqforCandidateAlgo);
		while (pqforCandidateAlgo.peek().cost == 0) { // 비용이 0이라는 조건만
										// 가지고 판단하는 것은 너무
										// 단순함. 나중에 고칠 필요
										// 있음.
			
			System.out.println("pq. " + pqforCandidateAlgo);
			linkOperatorWithAlgorithm(queryOperator, pqforCandidateAlgo.peek().name);
			pqforCandidateAlgo.poll();
			System.out.println("1.pq.size(): " + pqforCandidateAlgo.size());
			System.out.println("operator has been linked with algorithm complete");
//			return true;
		}
		System.out.println("2.pq.size(): " + pqforCandidateAlgo.size());
		
		if (pqforCandidateAlgo.peek().cost != 0)
			{return false;}
		else {return true;}
	}

	public double execute(String queryOperator, double a1, double a2) {

		String dbUrl = "jdbc:mysql://127.0.0.1:3306/elab?characterEncoding=UTF-8";
		String dbUsername = "librarian";
		String dbPassword = "40211";
		Double output = null;

		Statement statement = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl,
					dbUsername, dbPassword);
			statement = connection.createStatement();
			String str = "select * from Algorithm where Name='" + queryOperator
					+ "';";

			ResultSet rs = statement.executeQuery(str);

			System.out.println("query:" + queryOperator);
			System.out.println("a1:" + a1);
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

			rs.last();
			rs.beforeFirst();
			// source 를 받아왔으면 그것에 피연산자를 넣어서
			// 값을 구한다.
			// 즉, 입력은 피연산자이고 결과같은 연산
			// 결과이다.
			// source의Java 파싱이 필요하다.

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

//			File helloWorldJava = new File(System.getProperty("user.dir") + "/"
//					+ className + ".java");// ("./add.java");
			File helloWorldJava = new File("./"
					+ className + ".java");// ("./add.java");
			System.out.println("here 5-2 in connect");
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
								new URL[] { new File("./").toURI()
										.toURL() });
						System.out.println("Yipe2: classLoader.path: "+ new File("./").toURI()
								.toURL().toString());
						// Load the class from the classloader by name....
						System.out.println("className: "+className);
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

				// String jcp = System.getProperty("java.class.path");
				// jcp += ":.";
				// System.out.println("java.class.path:"+jcp);
				// System.setProperty("java.class.path", jcp);

//				URLClassLoader classLoader = new URLClassLoader(
//						new URL[] { new File(System.getProperty("user.dir")
//								+ "/").toURI().toURL() });
				URLClassLoader classLoader = new URLClassLoader(
						new URL[] { new File(
								"./").toURI().toURL() });
				System.out.println("class loader location: " + classLoader.toString());
				Class<?> passedClass = classLoader.loadClass(strClass);// "add"

				System.out.println("here is 7.5");
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
				Object[] arglist = new Object[] { (a1), (a2) };
				// invoke method!!
				System.out.println("here is 10");
				output = (Double) meth.invoke(dummyto, arglist); // method,
																	// class
																	// instance
				classLoader.close();
				
				System.out.println("here is 11");
				System.out.println(arglist[0] + " " + queryOperator + " "
						+ arglist[1] + " = " + output);

				System.out.println("here is 12");
				return (double) (output);

				// 만약 외부지식 가운데 subclass가 있다면
				// 입력자료를 확인하여 classification을 수행하라.
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
