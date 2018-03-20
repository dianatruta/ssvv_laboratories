package repository;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MemberRepositoryTest {

    private MemberRepository memberRepository;

    @Before
    public void setUp() throws Exception {
        memberRepository = new MemberRepository("membersF.txt");
    }

    @Test
    public void testCheckIfExists() {
        assertTrue(memberRepository.checkIfExists(1));
    }

}
