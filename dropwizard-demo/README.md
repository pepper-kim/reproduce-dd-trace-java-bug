# About bug reproduce,
After Step4 you can't see the stack trace of where pinned happen(it should be printed by Djdk.tracePinnedThreads VM option) and can't see "pinned ended!" printed at console also. But if you run step4 after running step3 without -javaagent:lib/dd-java-agent-1.49.0.jar, you can see the stack trace where pinned happened.

If you see thread dump made by jstack, you can see dd-trace-monitor thread's state changed after pinned happen(This was the main reason why i thought dd-java-agent might be the problem and tried to remove it).
However when reproducing one with spring framework, i found dd-telemetry's state changed. Supposing there might be another reason that make this. The one thing i can say is that dd-java-agent.jar is making this issue.

[dropwizard_demo_before_pinned_jstack_2025-05-19_11-04-36.txt](dump%2Fdropwizard_demo_before_pinned_jstack_2025-05-19_11-04-36.txt)
[dropwizard_demo_after_pinned_jstack_2025-05-19_11-04-47.txt](dump%2Fdropwizard_demo_after_pinned_jstack_2025-05-19_11-04-47.txt)

#### Step 1
```
docker run --rm\
  -p 8126:8126\
  -e SNAPSHOT_CI=0\
  -v $PWD/tests/snapshots:/snapshots\
  ghcr.io/datadog/dd-apm-test-agent/ddapm-test-agent:latest
```

#### Step 1
`mvn clean install`

#### Step 3
`java -jar -javaagent:lib/dd-java-agent-1.49.0.jar -Djdk.tracePinnedThreads=full target/dropwizard-demo-1.0-SNAPSHOT.jar server config.yml`

#### Step 4
`curl http://localhost:8080/pinned/test`
