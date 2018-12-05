import com.dominicswaine.seg_agile_project.Logic.Board;
import com.dominicswaine.seg_agile_project.Logic.Hole;
import com.dominicswaine.seg_agile_project.Logic.Korgool;
import com.dominicswaine.seg_agile_project.Logic.Parser;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParserTest {

    @Test
    public void testWritingToDefaultNewGameFile() {
        Board b = new Board();
        ArrayList<Korgool> defaultList = new ArrayList<>();
        for(int i = 0 ; i < 9 ; ++i ){
            defaultList.add(new Korgool());
        }
        for (Hole h : b.getHoles()) {
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
        }

        Parser p = new Parser(b);

        p.addContent();

        p.writeToFile("src/test/resources/game_test_files/new_game_test.json");
        File file1 = new File("C:\\Users\\horia\\Desktop\\SEG-Agile-Project\\src\\main\\resources\\game_files\\new_game.json");
        File file2 = new File("C:\\Users\\horia\\Desktop\\SEG-Agile-Project\\src\\test\\resources\\game_test_files\\new_game_test.json");
        try {
            boolean areEqual = FileUtils.contentEquals(file1,file2);
            assertTrue(areEqual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
