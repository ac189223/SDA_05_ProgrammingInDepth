package AntProblemTwo;

import java.util.stream.Collectors;

public class test {

    public static void main(String[] args) {
        HoneyComb comb1 = new HoneyComb(4);
        comb1.createHoneyComb();
        int size = comb1.getGrid().size();
        for (int i = 0 ; i < size ; i ++) {
            comb1.getGrid().get(i).forEach(cell -> System.out.print(cell.toString()));
            System.out.println();

        }
        System.out.println();
        System.out.println();
        System.out.println("find" + comb1.findCellByID(20).getId() + "," + comb1.findCellByID(20).getrowNum() );

        int[] n = comb1.findNeighbours(6);
        System.out.println(n[0] + "," + n[1] + "," + n[2] + "," + n[3]+ "," + n[4]+ "," + n[5]);

        int[] n2 = comb1.findNeighbours(31);
        System.out.println(n2[0] + "," + n2[1] + "," + n2[2] + "," + n2[3]+ "," + n2[4]+ "," + n2[5]);


        Router router = new Router() ;
        router.createGrid(6);
        System.out.println("ROUUTEERR");
        int size1 = router.getHoneyComb().getGrid().size();
        for (int i = 0 ; i < size ; i ++) {
            router.getHoneyComb().getGrid().get(i).forEach(cell -> System.out.print(cell.toString()));
            System.out.println();

        }




        int[] n3 = router.getHoneyComb().findNeighbours(6);
        System.out.println("3: " + n3[0] + "," + n3[1] + "," + n3[2] + "," + n3[3]+ "," + n3[4]+ "," + n3[5]);
        router.blockCell(15);
        router.blockCell(16);
        router.blockCell(17);
        router.blockCell(19);

        int[] n4 = router.getHoneyComb().findNeighbours(6);
        System.out.println("4: " + n4[0] + "," + n4[1] + "," + n4[2] + "," + n4[3]+ "," + n4[4]+ "," + n4[5]);


        System.out.println("======================");
        router.findPaths(1,45,0,6) ;
        router.getPathList().forEach(num -> System.out.print(num + ","));
        System.out.println();
        if (router.getPathList().size()== 0 ) {System.out.println("no");}
        else { System.out.println("min path is : " +  router.getPathList().stream().sorted().collect(Collectors.toList()).get(0)); }
    }

}