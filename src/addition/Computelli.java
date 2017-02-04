/*
 *  CompIntelli.java
 *  
 *  created by Sung H. Park
 *  
 *  implements a computational intelligence
 */
package addition;

import java.sql.ResultSet;
import java.util.Stack;

public class Computelli {

	Computelli() {
	}

	// A* algorithm
	// 가정 1: 그래프가 존재한다
	// 가정 2: 시작노드와 목표노드가 주어진다. 그래서, 시작노드가 무엇인지 목표노드가 무엇인지 알 수 있다.
	// 여기서, Open과 Closed는 리스트이고, 하나의 스택이 필요하다.
	// 1. 시작노드를 Open에 넣는다. 시작노드의 penalty 함수 식에 따라 계산한다.
	// 2. Open에 노드가 남아 있는 동안 다음을 반복한다.
	// 2.1 Open에서 예측Penalty ^p값이 최소인 노드를 꺼내어 Closed에 넣는다. 이 노드를 n이라한다. 만일 동일한 예측
	// penalty ^p를 가지고 있는 노드가 여러 개 있을 때에는
	// 임의로 선택하되 목표노드가 있다면 우선적으로 선택한다.
	// 2.2 노드 n이 목표노드라면 탐색은 성공적으로 끝난다. 포인터를 역으로 추적하면 탐색경로를 얻을 수 있다.
	// 2.3 노드 n을 확장하여 후계노드 n1, n2, …, ni를 생성한다. 이들 후계 노드에 부노모드인 노드 n을 가리키는 포인터를
	// 첨부한다.
	// 2.4 각각의 후계노드에 대한 예측 penalty 함수, ^p(n1), ^p(n2),…, ^p(ni)를 계산하여 (어디에?)
	// 첨부한다.
	// 2.5 각각의 후계노드 nk, k = 1, 2, …, i 에 대하여
	// 2.5.1 동일한 노드가 Open에 이미 존재한다면 (그 노드를 nold라 하자)
	// 2.5.1.1 ^p(nold)가 ^p(nk)보다 작다면 nk는 버린다.
	// 2.5.1.2 그렇지 않으면, nold를 open에서 제거한다.
	// 2.5.2 동일한 노드가 Closed에 이미 존재한다면 (그 노드를 n’old라 하자)
	// 2.5.2.1 ^p(n’old)가 ^p(nk)보다 작다면 nk는 버린다.
	// 2.5.2.2 그렇지 않으면, n’old의 부모포인터가 노드 n을 가리키도록 수정하고 평가함수를 ^p(nk)으로 수정한다. 또한
	// n’old의 모든 후계노드에 대한 경로비용 g가 변화하였으므로 이를 수정한다.
	// 2.5.3 동일한 노드가 Open이나 Closed에 존재하지 않으면 nk를 Open에 삽입한다.
	// 3. 탐색은 실패로 끝난다.
	public static void compute(Node finalNode) {
		while (finalNode.value.isEmpty()) {
			for (int i = 0; i < finalNode.inEdge.size(); i++) {
				if (finalNode.inEdge.get(i).from_node.value.equals("propertyOf")) {

				}
				finalNode = finalNode.inEdge.get(0).from_node;

			}

		}

	}

	public static double compute(String queryOperator, Stack<Object> inputStack, Stack<Object> outputStack) {
		// 먼저, queryOperator에 해당하는 알고리즘에 소스가 있는지 없는지 확인한다. 문제 네트워크를 만들어라
		// double a1 = Double.parseDouble((String) stack.pop());
		// double a2 = Double.parseDouble((String) stack.pop());
		ResultSet rs = null;
		System.out.println("rs1:" + rs);
		double output;
		boolean output2 = true;

		ConnectionAlgoDB algoDB = new ConnectionAlgoDB();

		// 그렇지 않고, queryOperator에 해당하는 연산자에 소스가 없다면, 최적의 알고리즘 소스를 찾기위해 evaluate을
		// 할 것이다.
		// Unless the queryOperator exists in the algo DB, evaluation will be
		// made to find optimal algorithm source.

		// db connection and retrieval
		if ((ConnectionAlgoDB.existRelevantAlgorithm(queryOperator)) == false) {
			System.out.println("There is no relevant algorithm related to the operated!! "
					+ "To find a relevant algorithm, there should be ground truth "
					+ "corresponing to the operator. Please check if there is ground truth for the operator");

			if (ConnectionGTBase.existRelevantGroundTruth(queryOperator) == false) {
				System.out.println("There is no relevant ground truth related to the operator!!"
						+ "Please enter the ground truth for the operator");

				// take the ground truth from the external source

				return 10e+16;
			}

			// get candidate algorithms
			// rs = algoDB.getCandidateAlgorithms(queryOperator);
			// // return output;
			// if (rs == null) {
			// System.out.println("There is no relevalnt candidate
			// algorithms!!");
			// return 10e+16;
			// } else {
			// System.out.println("There are some candidates. Now we start
			// evaluating!");
			// output2 = algoDB.evaluate(queryOperator, a1, a2);
			// System.out.println("rs2:" + rs);
			// if (!output2) {return 10e+16;}
			//
			// }
			// System.out.println("Please select one from candidates or directly
			// type in your knowledge");
			ConnectionKBase kbase = new ConnectionKBase();
			// get candidate algorithms
			rs = ConnectionKBase.getRelevantKnowledge(queryOperator);
			// return output;
			// boolean rs2 =
			// ConnectionKBase.existRelevantKnowledge(queryOperator);
			if (rs == null) {
				System.out.println("There is no relevalnt candidate knowledge!! Please enter some knowledge");
				return 10e+16;
			} else {
				System.out.println("There are some knowledge. Now we start evaluating knowledge!");
				output2 = kbase.evaluateKnowledgeRelatedToOperator(queryOperator);
				System.out.println("rs2:" + rs);
				if (!output2) {
					return 10e+16;
				}

			}
			System.out.println("Please select one from candidates or directly type in your knowledge");
		}
		// ÎßåÏïΩ, queryOperator Ïóê ÏÜåÏä§Í∞Ä Ïûò Ïó∞Í≤∞ÎêòÏñ¥ ÏûàÎäî
		// Í≤ΩÏö∞, Î∞îÎ°ú dbÏóê Ï†ëÏÜçÌï¥ÏÑú
		// ÏïåÍ≥†Î¶¨Ï¶ò ÏÜåÏä§Î•º Í∞ÄÏ†∏ÏôÄÏÑú Ïã§ÌñâÌï† Í≤É
		if (output2) {
			System.out.println("here is connect!");
//			output = algoDB.execute(queryOperator, inputStack, outputStack);
//			System.out.println("결과: " + output);
			output = 0.0;
			return output;
		} else {
			return 10e+16;
		}

	}

	public static double compute2(String queryOperator, double a1, double a2, double a3) {
		System.out.println("Here is compute2~~");
		// Î®ºÏ†Ä, queryOperatorÏóê Ìï¥ÎãπÌïòÎäî ÏïåÍ≥†Î¶¨Ï¶òÏóê ÏÜåÏä§Í∞Ä
		// ÏûàÎäîÏßÄ ÏóÜÎäîÏßÄ ÌôïÏù∏ÌïúÎã§.
		ResultSet rs = null;
		System.out.println("rs1:" + rs);
		double output = 0.0;
		/*
		 * ConnectionAlgoDB algoDB = new ConnectionAlgoDB(); if
		 * ((algoDB.existRelevantAlgorithm(queryOperator, a1, a2))==false) { //
		 * Í∑∏Î†áÏßÄ ÏïäÍ≥†, queryOperatorÏóê Ìï¥ÎãπÌïòÎäî Ïó∞ÏÇ∞ÏûêÏóê
		 * ÏÜåÏä§Í∞Ä ÏóÜÎã§Î©¥, ÏµúÏ†ÅÏùò ÏïåÍ≥†Î¶¨Ï¶ò ÏÜåÏä§Î•º Ï∞æÍ∏∞ÏúÑÌï¥
		 * evaluateÏùÑ Ìï† Í≤ÉÏù¥Îã§. // db connection and retrieval
		 * System.out.println("here is evaluate!");
		 * algoDB.evaluate(queryOperator, a1, a2); //return output; } {
		 * System.out.println("rs2:"+rs); // ÎßåÏïΩ, queryOperator Ïóê ÏÜåÏä§Í∞Ä
		 * Ïûò Ïó∞Í≤∞ÎêòÏñ¥ ÏûàÎäî Í≤ΩÏö∞, Î∞îÎ°ú dbÏóê Ï†ëÏÜçÌï¥ÏÑú //
		 * ÏïåÍ≥†Î¶¨Ï¶ò ÏÜåÏä§Î•º Í∞ÄÏ†∏ÏôÄÏÑú Ïã§ÌñâÌï† Í≤É System.out.println(
		 * "here is connect!"); output = algoDB.connect(queryOperator, a1, a2);
		 * System.out.println ("Í≤∞Í≥º: " + a1 +" "+queryOperator+" "+a2 + " = "
		 * + output); }
		 */
		return output;

	}

	public static double compute3(Node2 node) {
		System.out.println("Here is compute2~~");
		// Î®ºÏ†Ä, queryOperatorÏóê Ìï¥ÎãπÌïòÎäî ÏïåÍ≥†Î¶¨Ï¶òÏóê ÏÜåÏä§Í∞Ä
		// ÏûàÎäîÏßÄ ÏóÜÎäîÏßÄ ÌôïÏù∏ÌïúÎã§.
		ResultSet rs = null;
		System.out.println("rs1:" + rs);
		// compute(node.value,1,2);
		double output = 0.0;
		/*
		 * ConnectionAlgoDB algoDB = new ConnectionAlgoDB(); if
		 * ((algoDB.existRelevantAlgorithm(queryOperator, a1, a2))==false) { //
		 * Í∑∏Î†áÏßÄ ÏïäÍ≥†, queryOperatorÏóê Ìï¥ÎãπÌïòÎäî Ïó∞ÏÇ∞ÏûêÏóê
		 * ÏÜåÏä§Í∞Ä ÏóÜÎã§Î©¥, ÏµúÏ†ÅÏùò ÏïåÍ≥†Î¶¨Ï¶ò ÏÜåÏä§Î•º Ï∞æÍ∏∞ÏúÑÌï¥
		 * evaluateÏùÑ Ìï† Í≤ÉÏù¥Îã§. // db connection and retrieval
		 * System.out.println("here is evaluate!");
		 * algoDB.evaluate(queryOperator, a1, a2); //return output; } {
		 * System.out.println("rs2:"+rs); // ÎßåÏïΩ, queryOperator Ïóê ÏÜåÏä§Í∞Ä
		 * Ïûò Ïó∞Í≤∞ÎêòÏñ¥ ÏûàÎäî Í≤ΩÏö∞, Î∞îÎ°ú dbÏóê Ï†ëÏÜçÌï¥ÏÑú //
		 * ÏïåÍ≥†Î¶¨Ï¶ò ÏÜåÏä§Î•º Í∞ÄÏ†∏ÏôÄÏÑú Ïã§ÌñâÌï† Í≤É System.out.println(
		 * "here is connect!"); output = algoDB.connect(queryOperator, a1, a2);
		 * System.out.println ("Í≤∞Í≥º: " + a1 +" "+queryOperator+" "+a2 + " = "
		 * + output); }
		 */
		return output;

	}

	public static double learn(String queryOperator, double a1, double a2, double a3) {
		double output = ConnectionGTBase.connect(queryOperator, a1, a2, a3);

		return output;

	}

	public static double inverse(String unknown, String queryOperator, double a1, double a2) {
		//
		ResultSet rs = null;
		System.out.println("rs1:" + rs);
		double output = 0.0;
		ConnectionInverse inverseDB = new ConnectionInverse();
		if ((inverseDB.existRelevantAlgorithm(queryOperator, a1, a2)) == false) {
			// Í∑∏Î†áÏßÄ ÏïäÍ≥†, queryOperatorÏóê Ìï¥ÎãπÌïòÎäî Ïó∞ÏÇ∞ÏûêÏóê
			// ÏÜåÏä§Í∞Ä ÏóÜÎã§Î©¥, ÏµúÏ†ÅÏùò ÏïåÍ≥†Î¶¨Ï¶ò ÏÜåÏä§Î•º
			// Ï∞æÍ∏∞ÏúÑÌï¥ evaluateÏùÑ Ìï† Í≤ÉÏù¥Îã§.
			// db connection and retrieval
			System.out.println("here is evaluate!");
			inverseDB.evaluate(queryOperator, a1, a2);
			// return output;
		}

		{
			System.out.println("컴퓨터 사고를 적용하면");

			System.out.println("rs2:" + rs);
			// ÎßåÏïΩ, queryOperator Ïóê ÏÜåÏä§Í∞Ä Ïûò Ïó∞Í≤∞ÎêòÏñ¥ ÏûàÎäî
			// Í≤ΩÏö∞, Î∞îÎ°ú dbÏóê Ï†ëÏÜçÌï¥ÏÑú
			// ÏïåÍ≥†Î¶¨Ï¶ò ÏÜåÏä§Î•º Í∞ÄÏ†∏ÏôÄÏÑú Ïã§ÌñâÌï† Í≤É

			System.out.println("here is connect!");
			for (double i = -8; i < 8; i++) {
				output = inverseDB.connect(unknown, queryOperator, i, a1);
				System.out.println("output:" + output + ",a2:" + a2);
				if (output == a2) {
					System.out.println("결과: " + i + " " + queryOperator + " " + a1 + " = " + a2);
					output = i;
					break;

				}
			}
		}

		System.out.println("Here is inverse!");
		// 법칙 논리를 적용한 부분
		{
			System.out.println("법칙 및 논리를 적용하면");
			// a1의 역원을 구한다. 이때 queryOperator의 항등원에 대한 지식이 필요하다
			for (double i = -8; i < 8; i++) {
				output = inverseDB.connect(unknown, queryOperator, i, a1); // 항등원
																			// 찾기

				// 인간은 여기서 '더하기'의 항등원이 '0'이라는 사실을 알고 역원으로 동일한 '더하기'연산을 하여서
				// '항등원'이 만들어지는지 확인하는 과정을 거친다.
				// 이 부분이 컴퓨터가 자동으로 알아서 만들어줘야 하는 부분이다.
				// DB에서 이코드를 가져오든지 아니면 동적으로 만들어주든지 해야 한다.
				// DB에서 가져오는 코드는 너무 간단하다.
				// 만약 지식으로부터 동적으로 만드는 방법은 어떻게 해야 할까?

				if (output == 0) // '더하기' 연산을 통해 항등원이 되었는지 확인한다. 즉, 역원을 찾았는지
									// 확인한다.
									// 여기서, '0'은 사실 DB로부터 찾아와야 한다. 모든 연산에 대해서 이
									// 과정이
									// 일반화되어 적용될 수 있어야 한다.***
				{
					output = inverseDB.connect(unknown, queryOperator, i, a2);
					System.out.println("결과: " + output + " " + queryOperator + " " + a1 + " = " + a2);
					break;
				}
			}

		}

		{
			System.out.println(">-<>-<>-<>-<>-<>-<>-<>-<");
			System.out.println("컴퓨터 사고에 계산지능을 적용하면");

			Graph a = new Graph();

			// 2. 외부지식 네트워크 하나를 읽어온다. "나누기 역 곱하기"
			// 2. 외부지식 네트워크 하나를 읽어온다. "나누기 피연산자개수 2"

			a.findShortestPath(a.nodeList.get(0), a.nodeList.get(1), a);

			// 1. 일단, 입력받은 문제 문장을 해석-> parse tree -> antlr에서 담당
			// 2. 일반문장-> rdf -> graph
			// 3. 추론을 graph에서 최단 거리를 구하는 문제로 바꿔서 문제 해결

			System.out.println("rs2:" + rs);
			// ÎßåÏïΩ, queryOperator Ïóê ÏÜåÏä§Í∞Ä Ïûò Ïó∞Í≤∞ÎêòÏñ¥ ÏûàÎäî
			// Í≤ΩÏö∞, Î∞îÎ°ú dbÏóê Ï†ëÏÜçÌï¥ÏÑú
			// ÏïåÍ≥†Î¶¨Ï¶ò ÏÜåÏä§Î•º Í∞ÄÏ†∏ÏôÄÏÑú Ïã§ÌñâÌï† Í≤É

			// System.out.println("here is connect!");
			// for (double i = -8; i < 8; i++)
			// {
			// output = inverseDB.connect(unknown, queryOperator, i, a1);
			// System.out.println("output:"+output+",a2:"+a2);
			// if (output == a2)
			// {
			// System.out.println("결과: " + i + " " + queryOperator + " " + a1
			// + " = " + a2);
			// output = i;
			// break;
			//
			// }
			// }
		}

		return output;

	}

}
