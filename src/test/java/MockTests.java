import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Mockitoを用いたテスト実装
 *
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class MockTests {

    /** テスト対象のサービス実装 */
    @Spy
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    /** テスト対象が依存するリポジトリ */
    @Mock
    private UserRepository userRepository;

    /**
     * Mock共通の挙動を定義
     */
    @Before
    public void setupCommonMock() {
        when(userRepository.findUser(0)).thenReturn(null);
        when(userRepository.findUser(1)).thenReturn(new User(1, "Hello"));
    }

    /**
     * モックのテスト
     */
    @Test
    public void testMock() {
        final User userInput = new User(1, "Hello");

        // 対象サービスの挙動を確認
        final User userOutput = userServiceImpl.registerUser(userInput);
        assertEquals(1, userOutput.getId());
        assertEquals("Hello", userOutput.getName());

        // 内部リポジトリのコールを確認
        verify(userRepository, times(1)).storeUser(userInput);
        verify(userRepository, atMost(1)).findUser(1);
        verify(userRepository, never()).removeUser(anyInt());
    }

}
