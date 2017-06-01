package gu.admin.test;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import gu.common.FileDirStrVO;
import gu.common.FileVO;
import gu.common.SearchVO;

@Service
public class TestSvc {

    @Autowired
    private SqlSessionTemplate sqlSession;    
    @Autowired
    private DataSourceTransactionManager txManager;

    static final Logger LOGGER = LoggerFactory.getLogger(TestSvc.class);
    
    public Integer selectTestCount(SearchVO param) {
        return sqlSession.selectOne("selectTestCount", param);
    }
    
    public List<?> selectTestList(SearchVO param) {
        return sqlSession.selectList("selectTestList", param);
    }
    
    /**
     * 글 저장.
     * @param param 
     */
    public void insertTest(String codeFormType, TestVO param, List<FileVO> filelist) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);
        
        try {
            if ("U".equals(codeFormType)) {
                sqlSession.update("updateTest", param);
            } else {
                sqlSession.insert("insertTest", param);
            }
            txManager.commit(status);
        } catch (TransactionException ex) {
            txManager.rollback(status);
            LOGGER.error("insertTest");
        }
        
        for (FileVO f : filelist) {
        	f.setParentPK(param.getClassno());
           sqlSession.insert("insertBoard4File", f);
        }

    }
 
    public TestVO selectTestOne(TestVO param) {
        return sqlSession.selectOne("selectTestOne", param);
    }

    public void deleteTestOne(TestVO param) {
        sqlSession.delete("deleteTestOne", param);
    }

	public List<?> selectTestFileList(String param) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("selectTestFileList", param);
	}

	public int insertFileDirStrVO(FileDirStrVO param) {
		// TODO Auto-generated method stub
		return sqlSession.insert("insertFileDirStrVO", param);
	}

	public List<?> selectTestFileStr(String param) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("selectTestFileStr", param);
	}

	public int updateParentId(String param) {
		// TODO Auto-generated method stub
		return sqlSession.update("updateParentId", param);
	}



}
