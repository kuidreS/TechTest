package vserdiuk.tech.test.report;

import vserdiuk.tech.test.model.Instruction;

import java.util.List;

public interface Reporter {

    String report(List<Instruction> instructions);

}
