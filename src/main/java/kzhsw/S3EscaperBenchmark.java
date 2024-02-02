package kzhsw;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class S3EscaperBenchmark {

    @Param({"my-bucket", "photos/2024/02/02", "file.txt?versionId=3L137"})
    public String testString;

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void benchmarkEncode() {
        S3Escaper.encode(testString);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void benchmarkEncodeOptimized() {
        S3Escaper.encodeOptimized(testString);
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
