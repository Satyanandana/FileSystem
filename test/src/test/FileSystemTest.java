package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import fileSystem.CountingVisitor;
import fileSystem.Directory;
import fileSystem.FSElement;
import fileSystem.File;
import fileSystem.FileSearchVisitor;
import fileSystem.FileSystem;
import fileSystem.Link;
import fileSystem.SizeCountingVisitor;



public class FileSystemTest {
	
	FileSystem fileSystem ;
	Directory root ;
	
	Directory system ;
	File a ;
	File b ;
	File c ;
	Date createdDate;
	Directory home ;
	File d ;
	Link x;
	Directory picture ;
	File e ;
	File f ;
	Link y;
	Link z;
	Directory music ;
	SizeCountingVisitor sizeCountingVisitor;
	CountingVisitor countingVisitor;
	FileSearchVisitor fileSearchVisitor;
	
	@Before
	public void initialize(){
		fileSystem = FileSystem.getfileSystem();
		root = new Directory(null, "root", "Satya");
		fileSystem.setRoot(root);
		
	
		system = new Directory(root, "system", "satya");
		a = new File(system, "a.txt", "satya", 30);
		b = new File(system, "b.pdf", "satya", 60);
		c = new File(system, "c.txt", "satya", 80);
		createdDate = new Date();
	    home = new Directory(root, "home", "satya");
		d = new File(home, "d.pdf", "satya", 45);
		x = new Link(home, system, "Link to system directory", "satya");
		picture = new Directory(home, "picture", "satya");
		e = new File(picture, "e.pdf", "satya", 45);
		f = new File(picture, "f.txt", "satya", 65);
		y = new Link(picture, e, "Link to file e", "satya");
		z = new Link(picture, x, "Link to link x", "satya");
		music = new Directory(home, "music", "satya");
		
	}
	
	@Test
	public void testAcceptSizeCountingVisitor(){
		sizeCountingVisitor = new SizeCountingVisitor();
		root.accept(sizeCountingVisitor);
		int actualSize = sizeCountingVisitor.getTotalSize();
		int expectedSize = 325;
		assertThat(actualSize,is(expectedSize));
	}
	
	@Test
	public void testAcceptCountingVisitor(){
		countingVisitor = new CountingVisitor();
		root.accept(countingVisitor);
		int actualNoOfDirs = countingVisitor.getDirNum();
		int expectedNoOfDirs = 5;
		int actualNoOfFiles = countingVisitor.getFileNum();
		int expectedNoOfFiles = 6;
		int actualNoOfLinks = countingVisitor.getLinkNum();
		int expectedNoOfLinks = 3;
		assertThat(actualNoOfDirs,is(expectedNoOfDirs));
		assertThat(actualNoOfFiles,is(expectedNoOfFiles));
		assertThat(actualNoOfLinks,is(expectedNoOfLinks));
	}
	
	@Test
	public void testAcceptFileSearchVisitor(){
		fileSearchVisitor = new FileSearchVisitor(".pdf");
		root.accept(fileSearchVisitor);
		int actualNo = fileSearchVisitor.getFoundFiles().size();
		int expectedNo = 3;
		assertThat(actualNo,is(expectedNo));
	}
		
	@Test
	public void testFileGetSize(){
		int actualSize  = a.getSize();
		int expectedSize = 30;
		assertThat(actualSize,is(expectedSize));
	}
	
	@Test
	public void testFileGetDiskUtil(){
		int actualSize  = a.getDiskUtil();
		int expectedSize = 30;
		assertThat(actualSize,is(expectedSize));
	}
	
	@Test
	public void testFileGetType(){
		String actualType  = a.getType();
		String expectedType = "File";
		assertThat(actualType,is(expectedType));
	}
	
	@Test
	public void testFileIsLeaf(){
		boolean actualIsLeaf  = a.isLeaf();
		boolean expectedIsLeaf = true;
		assertThat(actualIsLeaf,is(expectedIsLeaf));
	}
	
	@Test
	public void testDirectoryGetSize(){
		int actualSize  = home.getSize();
		int expectedSize = 155;
		assertThat(actualSize,is(expectedSize));
	}
	
	@Test
	public void testDirectoryGetDiskUtil(){
		int actualSize  = home.getDiskUtil();
		int expectedSize = 0;
		assertThat(actualSize,is(expectedSize));
	}
	
	@Test
	public void testDirectoryGetType(){
		String actualType  = home.getType();
		String expectedType = "Directory";
		assertThat(actualType,is(expectedType));
	}
	
	@Test
	public void testDirectoryIsLeaf(){
		boolean actualIsLeaf  = music.isLeaf();
		boolean expectedIsLeaf = true;
		assertThat(actualIsLeaf,is(expectedIsLeaf));
	}
	
	@Test
	public void testDirectoryGetChildren(){
		ArrayList<FSElement> actualChildren = picture.getChildren();
		ArrayList<FSElement> ExpectedChildren = new ArrayList<>();
		ExpectedChildren.add(e);
		ExpectedChildren.add(f);
		ExpectedChildren.add(y);
		ExpectedChildren.add(z);
		assertArrayEquals(actualChildren.toArray(), ExpectedChildren.toArray());
	}
	
	@Test
	public void testLinkGetSize(){
		int actualSize  = z.getSize();
		int expectedSize = 170;
		assertThat(actualSize,is(expectedSize));
	}
	
	@Test
	public void testLinkGetDiskUtil(){
		int actualSize  = z.getDiskUtil();
		int expectedSize = 0;
		assertThat(actualSize,is(expectedSize));
	}
	
	@Test
	public void testLinkGetType(){
		String actualType  = x.getType();
		String expectedType = "Link";
		assertThat(actualType,is(expectedType));
	}
	
	@Test
	public void testLinkIsLeaf(){
		boolean actualIsLeaf  = y.isLeaf();
		boolean expectedIsLeaf = true;
		assertThat(actualIsLeaf,is(expectedIsLeaf));
	}
	
	@Test
	public void testLinkSetAndGetTarget(){
		y.setTarget(f);
		FSElement actualTarget = y.getTarget();
		FSElement expectedTarget = f;
		assertThat(actualTarget,is(expectedTarget));
	}
	
	@Test
	public void testFSElementGetParent(){
		music.setParent(home);
		Directory actualParent = music.getParent();
		Directory expectedParent = home;
		assertThat(actualParent,is(expectedParent));
	}
	
	@Test
	public void testFSElementGetName(){
		music.setName("music1");
		String actualName = music.getName();
		String expectedName = "music1";
		assertThat(actualName,is(expectedName));
	}
	
	@Test
	public void testFSElementGetOwner(){
		music.setOwner("varma");
		String actualName = music.getOwner();
		String expectedName = "varma";
		assertThat(actualName,is(expectedName));
	}
	
	@Test
	public void testFSElementGetCreatedDate(){
		Date actualCreatedDate = home.getCreated();
		Date expetedCreatedDate = createdDate;
		assertThat(actualCreatedDate,is(expetedCreatedDate));
	}
	
	@Test
	public void testFSElementGetLastModifiedDate(){
		Date lastModifyDate = new Date();
		music.setLastModified(lastModifyDate);
		Date actuallastModifyDate = music.getLastModified();
		assertThat(actuallastModifyDate,is(lastModifyDate));
	
	}
	
	@Test
	public void testFSElementSetRoot(){
		fileSystem.setRoot(home);
		Directory actualRoot = fileSystem.getRoot();
		Directory expectedRoot = home;
		fileSystem.showAllElements(home);
		assertThat(actualRoot,is(expectedRoot));
	}
}
