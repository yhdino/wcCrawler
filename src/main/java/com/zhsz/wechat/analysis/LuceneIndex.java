package com.zhsz.wechat.analysis;

import com.zhsz.wechat.tools.SysConfig;
import javafx.scene.shape.Path;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.lionsoul.jcseg.analyzer.JcsegAnalyzer;
import org.lionsoul.jcseg.tokenizer.core.JcsegTaskConfig;


import java.io.File;
import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public class LuceneIndex {
    private static String driverClassName;
    private static String url;
    private static String username;
    private static String password;

    private static final Version version = Version.LUCENE_6_5_1;
    private Directory directory = null;
    private DirectoryReader ireader = null;
    private Connection conn;

    static {
        driverClassName = SysConfig.getPropertiesValue("driverclassname");
        url = SysConfig.getPropertiesValue("jdbc.url");
        username = SysConfig.getPropertiesValue("jdbc.username");
        password = SysConfig.getPropertiesValue("jdbc.password");
    }

    public LuceneIndex() {
        //directory = getIndex(SysConfig.getPropertiesValue("defaultindexpath"));
        //directory = new RAMDirectory();
    }

    public IndexSearcher getSearcher(){
        try {
            if(ireader==null) {
                ireader = DirectoryReader.open(directory);
            } else {
                DirectoryReader tr = DirectoryReader.openIfChanged(ireader) ;
                if(tr!=null) {
                    ireader.close();
                    ireader = tr;
                }
            }
            return new IndexSearcher(ireader);
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection(){
        if(this.conn == null){
            try {
                Class.forName(driverClassName);
                conn = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    private Analyzer getAnalyzer(){
        JcsegAnalyzer analyzer = null;
        JcsegTaskConfig config = new JcsegTaskConfig(Class.class.getClass().getResource("/").getPath()+"/jcseg.properties");
        if(analyzer == null){
            return new JcsegAnalyzer(config.COMPLEX_MODE);
        }else{
            return analyzer;
        }
    }

    @SuppressWarnings("Since15")
    private IndexWriter getIndex(String indexDirectoryPath){
        IndexWriter iwriter = null;
        IndexWriterConfig iwConfig =  new IndexWriterConfig(getAnalyzer());
        iwConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        //this directory will contain the indexes

        try {
            directory = FSDirectory.open((new File(Class.class.getClass().getResource("/").getPath()+".."+String.valueOf(indexDirectoryPath))).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //create the indexer
        try {
            iwriter = new IndexWriter(directory, iwConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return iwriter;
    }

    public void createIndex(String sql){
        Connection conn = getConnection();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        IndexWriter iwriter = null;
        if(conn == null){
            System.out.println("get the connection error...");
            return ;
        }
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            iwriter = getIndex(SysConfig.getPropertiesValue("defaultindexpath"));
            while(rs.next()){
                String id = rs.getString(1);
                String removecontent = rs.getString(5);
                Document doc = new Document();
                doc.add(new TextField("id", id+"", Field.Store.YES));
                doc.add(new TextField("removecontent", removecontent+"",Field.Store.YES));
                iwriter.addDocument(doc);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                if(iwriter != null)
                    iwriter.close();
                rs.close();
                pstmt.close();
                if(!conn.isClosed()){
                    conn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public List<String> searchByTerm(String field, String keyword, int num) throws InvalidTokenOffsetsException, org.apache.lucene.queryparser.classic.ParseException {
        List list = new ArrayList();
        IndexSearcher isearcher = getSearcher();
        Analyzer analyzer =  getAnalyzer();
        //使用QueryParser查询分析器构造Query对象
        QueryParser qp = new QueryParser(field,analyzer);
        //这句所起效果？
        qp.setDefaultOperator(QueryParser.OR_OPERATOR);
        try {
            Query query = qp.parse(keyword);
            ScoreDoc[] hits;

            //注意searcher的几个方法
            hits = isearcher.search(query,100).scoreDocs;

            for (int i = 0; i < hits.length; i++) {
                Document doc = isearcher.doc(hits[i].doc);
                list.add(doc.get("id"));
            }
            System.out.println(list.toString());
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws InvalidTokenOffsetsException, org.apache.lucene.queryparser.classic.ParseException {
        LuceneIndex ld = new LuceneIndex();
        ld.createIndex("select * from postdetail");
        ld.searchByTerm("removecontent","南京", 100);
    }
}
