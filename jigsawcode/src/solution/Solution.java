package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;
import java.util.*;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {


    private int searchedNodesNum=0;    // 已访问节点数： 用以记录所有访问过的节点的数量    /**
    private HashSet<Object> visitedList;
    private LinkedList<JigsawNode> exploreList;



    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode)
    {
        this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);
        this.currentJNode = null;
        this.visitedList = new HashSet<>(1000);
        this.exploreList = new LinkedList<JigsawNode>();
        Set<JigsawNode> mark= new HashSet<>(1000);

        this.searchedNodesNum = 0;
        super.reset();
        exploreList.add(this.beginJNode);
        mark.add(this.beginJNode);//jianzhi

        while (this.searchedNodesNum < 29000 && !exploreList.isEmpty()) {
            this.currentJNode = exploreList.peek();
            this.exploreList.remove();
            if (this.currentJNode.equals(eNode)) {
                this.getPath();
                break;
            }
            mark.remove(currentJNode);
            this.visitedList.add(this.currentJNode);
            this.searchedNodesNum++;

            JigsawNode[] next = new JigsawNode[]{
                    new JigsawNode(this.currentJNode),
                    new JigsawNode(this.currentJNode),
                    new JigsawNode(this.currentJNode),
                    new JigsawNode(this.currentJNode)
            };
            for (int i = 0; i < 4; ++i) {
                if (next[i].move(i) && !mark.contains(next[i]) && !this.visitedList.contains(next[i])) {
                    exploreList.add(next[i]);
                    mark.add(next[i]);
                }
            }
        }

        System.out.println("Jigsaw BF Search Result:");
        System.out.println("Begin state:" + this.getBeginJNode().toString());
        System.out.println("End state:" + this.getEndJNode().toString());
        System.out.println("Solution Path: ");
        System.out.println(this.getSolutionPath());
        System.out.println("Total number of searched nodes:" + this.searchedNodesNum);
        System.out.println("Depth of the current node is:" + this.getCurrentJNode().getNodeDepth());
        return this.isCompleted();

    }



    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */

    public void estimateValue(JigsawNode jNode)
    {
        int s = 1 * getEstimateValue0(jNode) +
                4 * getEstimateValue1(jNode)[0] +
                2 * getEstimateValue1(jNode)[1] +
                8 * getEstimateValue2(jNode);
        jNode.setEstimatedValue(s);
    }
    //所有放错位的数码个数
    private int getEstimateValue0(JigsawNode jNode)
    {
        int v0 = 0;
        int dimension = JigsawNode.getDimension();
        for (int i = 1; i <= dimension * dimension; i++)
        {
            if (jNode.getNodesState()[i] + 1 != i)
                v0++;
        }
        return v0;
    }
    //所有放错位的数码与其正确位置的距离之和(两种距离)
    private int[] getEstimateValue1(JigsawNode jNode)
    {
        int v1[] = {0,0};
        int c0, r0, c1, r1;

        int dimension = JigsawNode.getDimension();
        int currentNodeState[] = jNode.getNodesState();
        int endNodeState[] = endJNode.getNodesState();

        for (int i = 1; i <= dimension * dimension; i++)
        {
            if (currentNodeState[i] != 0 && currentNodeState[i] != endNodeState[i])
            {
                r0 = (int) (i - 1) / dimension;
                c0 = (int) (i + 4) % dimension;
                for (int j = 0; j <= dimension * dimension; j++)
                {
                    if (currentNodeState[i] == endNodeState[j])
                    {
                        r1 = (int) (j - 1) / dimension;
                        c1 = (int) (j + 4) % dimension;
                        v1[0] += (Math.abs(r1 - r0) + Math.abs(c1 - c0));
                        v1[1] += (Math.pow(r1 - r0,2) + Math.pow(c1 - c0,2));
                        break;
                    }
                }
            }
        }
        return v1;
    }
    // 后续节点不正确的数码个数
    private int getEstimateValue2(JigsawNode jNode)
    {
        int v2 = 0;
        int dimension = JigsawNode.getDimension();
        for(int i =1 ; i<dimension*dimension; i++){
            if(jNode.getNodesState()[i]+1!=jNode.getNodesState()[i+1])
                v2++;
        }
        return v2;
    }
}
