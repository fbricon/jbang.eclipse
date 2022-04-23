package dev.jbang.eclipse.core.internal.process;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JBangInfoResult {

	private String backingResource;

	private List<String> resolvedDependencies;

	private List<String> sources;
	
	private Map<String,String> files;

	private List<JBangError> resolutionErrors;

	private String requestedJavaVersion;
	
	private List<String> compileOptions;
	
	private List<String> runtimeOptions;

	public List<String> getResolvedDependencies() {
		return resolvedDependencies;
	}

	public void setResolvedDependencies(List<String> resolvedDependencies) {
		this.resolvedDependencies = resolvedDependencies;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("backingResource: ");
		sb.append(backingResource);
		if (sources != null && !sources.isEmpty()) {
			sources.forEach(source -> {
				sb.append(System.lineSeparator()).append("additional source: ").append(source);
			});
		}
		if (files != null && !files.isEmpty()) {
			files.forEach((link, file) -> {
				sb.append(System.lineSeparator()).append("additional file: ").append(link).append("[").append(file).append("]");
			});
		}
		if (resolvedDependencies != null && !resolvedDependencies.isEmpty()) {
			sb.append(System.lineSeparator()).append("resolvedDependencies: [");
			sb.append(String.join("," + System.lineSeparator(), resolvedDependencies));
			sb.append("]");
		}
		if (requestedJavaVersion != null && !requestedJavaVersion.isBlank()) {
			sb.append(System.lineSeparator()).append("requestedJavaVersion: " + requestedJavaVersion);
		}
		if (compileOptions != null && !compileOptions.isEmpty()) {
			sb.append(System.lineSeparator()).append("compileOptions: " + compileOptions);
		}
		if (runtimeOptions != null && !runtimeOptions.isEmpty()) {
			sb.append(System.lineSeparator()).append("runtimeOptions: " + runtimeOptions);
		}
		if (hasErrors()) {
			sb.append(System.lineSeparator()).append("resolutionErrors: [");
			sb.append(getResolutionErrors().stream().map(Object::toString).collect(Collectors.joining(", ")));
			sb.append("]");
		}
		return sb.toString();
	}

	public String getBackingResource() {
		return backingResource;
	}

	public void setBackingResource(String backingResource) {
		this.backingResource = backingResource;
	}

	public List<JBangError> getResolutionErrors() {
		return resolutionErrors;
	}

	public void setResolutionErrors(List<JBangError> resolutionErrors) {
		this.resolutionErrors = resolutionErrors;
	}

	public boolean hasErrors() {
		return resolutionErrors != null && !resolutionErrors.isEmpty();
	}

	public String getRequestedJavaVersion() {
		return requestedJavaVersion;
	}

	public String getTargetRuntime() {
		if (requestedJavaVersion == null || requestedJavaVersion.isBlank()) {
			return null;
		}
		String version = requestedJavaVersion.endsWith("+") ? requestedJavaVersion.substring(0, requestedJavaVersion.length() - 1) : requestedJavaVersion;
		try {
			int v = Integer.parseInt(version);
			if (v < 9) {
				version = "1." + v;
			}
			return "JavaSE-" + version;
		} catch (NumberFormatException e) {
		}
		return null;
	}

	public void setRequestedJavaVersion(String javaVersion) {
		this.requestedJavaVersion = javaVersion;
	}

	public List<String> getSources() {
		return sources;
	}

	public void setSources(List<String> sources) {
		this.sources = sources;
	}

	public Map<String, String> getFiles() {
		return files;
	}

	public void setFiles(Map<String, String> files) {
		this.files = files;
	}

	public class Resource {
		public String originalResource;
		public String backingResource;
	}

	public class File extends Resource {
		public String target;
	}
	
	public class Source extends Resource {
		public List<Source> sources;
	}

	public List<String> getCompileOptions() {
		return compileOptions;
	}

	public void setCompileOptions(List<String> compileOptions) {
		this.compileOptions = compileOptions;
	}

	public List<String> getRuntimeOptions() {
		return runtimeOptions;
	}

	public void setRuntimeOptions(List<String> runtimeOptions) {
		this.runtimeOptions = runtimeOptions;
	}
	
	
}