package fileSystem;

public interface FSVisitor {
	public void visit(Directory directory);
	public void visit(Link link);
	public void visit(File file);
}
