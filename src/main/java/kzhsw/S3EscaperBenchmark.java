package kzhsw;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class S3EscaperBenchmark {

    @Param({
        "my-bucket",
        "photos/2024/02/02",
        "file.txt?versionId=3L137",
        "user@domain.com",
        "document.pdf",
        "music/2024/album/song.mp3",
        "video/2024/movie.mp4?versionId=1A2B",
        "data/archive.zip",
        "application/database.sqlite",
        "folder/subfolder/file.txt",
        "special-characters/!$&'()*+,/:;=@[]",
        "emoji/😀😃😄😁😆😅😂🤣",
        "non-english/こんにちは/안녕하세요",
        "long-string/" + "a".repeat(1000),
        "deep-nesting/" + "folder/".repeat(100) + "file"
    })
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
