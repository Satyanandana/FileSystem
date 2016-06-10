package fileSystem;

public class SizeCountingVisitor implements FSVisitor {
	private int totalSize = 0;

	public int getTotalSize() {
		return totalSize;
	}

	@Override
	public void visit(Directory directory) {
		totalSize += directory.getDiskUtil();
	}

	@Override
	public void visit(Link link) {
		totalSize += link.getDiskUtil();

	}

	@Override
	public void visit(File file) {
		totalSize += file.getDiskUtil();
	}

}
