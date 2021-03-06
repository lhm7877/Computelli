package addition;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class ReflectionMaker {
	// Stack<Object> inputStack;
	Stack<Object> outputStack;

	public ReflectionMaker(Stack<Object> outputStack2) {
		this.outputStack = outputStack2;
	}

	public File writeSource(String className, String source, String path) {
		File helloWorldJava = new File(path + className + ".java");// ("./add.java");
		if (helloWorldJava.getParentFile().exists() || helloWorldJava.getParentFile().mkdirs()) {
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(helloWorldJava));
				writer.write(source);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return helloWorldJava;
	}

	public boolean compileSource(File file) {
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

		List<String> optionList = new ArrayList<String>();
		optionList.addAll(Arrays.asList("-classpath", System.getProperty("java.class.path")));
		// optionList.addAll(Arrays.asList("C:/Program
		// Files/Java/jre1.8.0_77/lib/javaparser-core-3.0.0-alpha.3.jar"));
		// optionList.add("-classpath");
		// optionList.add("java.class.path");
		// optionList.add("C:/Program
		// Files/Java/jre1.8.0_77/lib/javaparser-core-3.0.0-alpha.3.jar;"+"C:/Users/Hooo/workspace/Computelli/bin/addition"+";C:/Users/Hooo/workspace/rdf-prog/bin/ke/;");
		// System.out.println("!!!!!!!!!!!!!!!!!ClassPath 목록");
		// for(int i =0; i<optionList.size(); i++){
		// System.out.println(optionList.get(i).toString());
		// }
		// System.out.println();

		// System.out.println(ConnectionAlgoDB.class.getResource("").getPath());
		Iterable<? extends JavaFileObject> compilationUnit = fileManager
				.getJavaFileObjectsFromFiles(Arrays.asList(file));
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, optionList, null,
				compilationUnit);

		boolean success = task.call();
		if (!success) {
			for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
				System.err.format("Error on line %d in %s", diagnostic.getLineNumber(), diagnostic);
			}
			System.out.println();
			System.out.println();
		}
		return success;
	}

	public URLClassLoader sourceClassLoader(String path) throws MalformedURLException {
		URLClassLoader classLoader = new URLClassLoader(new URL[] { new File(path).toURI().toURL() });
		return classLoader;
	}

	public Parameter makeParObject(URLClassLoader urlClassLoader, String className, String strMethod, int numInArgs,
			String inArgType, Parameter parameter, int index, Object paraObject) {
		try {
			// Class<?> loadedClass;
			// Object object;
			// loadedClass = urlClassLoader.loadClass(className);
			// // Class<?> partypes = urlClassLoader.loadClass(strMethod);
			// object = loadedClass.newInstance();
			// Method meth2 = loadedClass.getMethod(strMethod, new
			// Class[]{String.class});
			// inputStack.push("박성희, (2016), \"KE\", 정보관리학회, 33, (3), pp. 22-40
			// ");
			// meth2.invoke(object, inputStack.pop().toString());

			parameter.setParTypes(urlClassLoader.loadClass("addition." + className), index);
			// 이부분을 새로 생성하는게 아니라 객체를 전달할 수 있도록 해야함 -> 2017.08.10 해결
			// parameter.setParObj(parameter.partypes[index].newInstance(),
			// index);

			if (paraObject.getClass() == java.lang.String.class) {
				Object obj = parameter.partypes[index].newInstance();
				Class<?> parameterOfParameterType = urlClassLoader.loadClass(inArgType);
				Method meth = parameter.partypes[index].getMethod(strMethod, parameterOfParameterType);
				meth.invoke(obj, paraObject);// inargType은 string이어야만 하기로 되어있다.
				parameter.setParObj(obj, index);
			}else{
				parameter.setParObj(paraObject, index);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parameter;
	}

	public Object sourceRun(URLClassLoader urlClassLoader, String className, String inArgType, int numInArgs,
			String strMethod, Parameter parameter) {
		Object result = "";
		try {
			Class<?> loadedClass = urlClassLoader.loadClass("addition." + className);// "add"\
			Object obj = loadedClass.newInstance();
			Object obj2 = null;
			Method meth2 = null;
			// Method meth = null;
			if (!(inArgType == null)) {
				String type[] = inArgType.split(",");
				Class<?>[] partypes = new Class[numInArgs];
				// for (int i = 0; i < numInArgs; i++) {
				// parameter.get(i).partypes =
				// urlClassLoader.loadClass(type[i]);

				// Object testObj = partypes[i].newInstance();
				// Method meth2 = partypes[i].getMethod("setText", new
				// Class[] { String.class });
				// meth2.invoke(testObj, "TestString2");
				// }
				// meth.invoke(obj, testObj);
				// Ref2 ref2 = new Ref2();

				// Method[] m = loadedClass.getDeclaredMethods();
				// System.out.println("메소드들!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				// for(int j =0; j<m.length; j++){
				// System.out.println(m[j].getName());
				// System.out.println(m[j].getParameters().toString());
				// 투}

				Method meth = loadedClass.getMethod(strMethod, parameter.partypes);
				// System.out.println(parameter.get(i).parObj.toString());
				// result = (String) meth.invoke(obj, parameter.parObj);
				// 2017.08.09 아래처럼 수정
				result = meth.invoke(obj, parameter.parObj);

				// Object parObj = partypes[i].newInstance();
				// meth2.invoke(obj2, "TestString2");
				// if (type[i].equals("java.lang.String")) {
				// meth.invoke(obj, inputStack.pop().toString());
				// } else {
				// meth.invoke(obj, parObj);
				// }
				// partypes[i] = urlClassLoader.loadClass(type[i]);
				// meth2 = partypes[i].getMethod("setText", new Class[] {
				// String.class });
				// obj2 = partypes[i].newInstance();

				// }
				// meth = loadedClass.getMethod(strMethod, partypes);
				// meth2.invoke(obj2, "TestString2");
				// meth.invoke(obj, obj2);
			}
			urlClassLoader.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return result;

	}

	public void sourceRun2(URLClassLoader urlClassLoader, String className, String inArgType, int numInArgs,
			String strMethod, Stack<String> outputStack) {
		try {
			Class<?> loadedClass = urlClassLoader.loadClass(className);// "add"\
			Object obj = loadedClass.newInstance();
			if (!(inArgType == null)) {
				String type[] = inArgType.split(",");
				// Class<?>[] partypes = new Class[numInArgs];
				for (int i = 0; i < numInArgs; i++) {
					// parameterAr.get(i).partypes[0] =
					// urlClassLoader.loadClass(type[i]);
					// Method meth = loadedClass.getMethod(strMethod,
					// parameterAr.get(i).partypes);

					// Object testObj = partypes[i].newInstance();
					// Method meth2 = partypes[i].getMethod("setText", new
					// Class[] { String.class });
					// meth2.invoke(testObj, "TestString2");

					// meth.invoke(obj, testObj);

					Method meth = loadedClass.getMethod(strMethod, new Class[] { String.class });
					// System.out.println(parameterAr.get(i).parObj.toString());
					meth.invoke(obj, outputStack.pop());

					// Object parObj = partypes[i].newInstance();
					// meth2.invoke(obj2, "TestString2");
					// if (type[i].equals("java.lang.String")) {
					// meth.invoke(obj, inputStack.pop().toString());
					// } else {
					// meth.invoke(obj, parObj);
					// }

					// Method meth = null;
					// partypes[i] = urlClassLoader.loadClass(type[i]);
					// meth = loadedClass.getMethod(strMethod, partypes);
					// Method meth2 = partypes[i].getMethod("setText", new
					// Class[] { String.class });
					// Object obj2 = partypes[i].newInstance();
					// meth2.invoke(obj2, "TestString2");
					// meth.invoke(obj, obj2);

				}

			}
			urlClassLoader.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println(e.getCause());
			e.printStackTrace();
		}

	}
}
