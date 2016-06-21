# Spring Data - JpaRepository/return Stream/Connection abandoned

This repository is a demo how one gets this error when working in non-transactional method with Springs JpaRepository returning a Stream:

```
2016-06-21 10:45:49.689  INFO 34109 --- [           main] my.test.StreamErrorAppRunner             : Started StreamErrorAppRunner in 2.957 seconds (JVM running for 3.301)
2016-06-21 10:45:50.770  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=1, name='new name0.7969572103643603'}
2016-06-21 10:45:50.773  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=2, name='new name0.6174306548575886'}
2016-06-21 10:45:50.774  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=3, name='new name0.19958778247813014'}
2016-06-21 10:45:50.777  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=4, name='new name0.3495512990270381'}
2016-06-21 10:45:50.779  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=5, name='new name0.09954225958072527'}
2016-06-21 10:45:50.780  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=6, name='new name0.6353995376332727'}
2016-06-21 10:45:50.782  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=7, name='new name0.1087949560923871'}
2016-06-21 10:45:50.783  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Finished reading the stream. It should be closed by now.
2016-06-21 10:45:51.890  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=1, name='new name0.4580903297121123'}
2016-06-21 10:45:51.893  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=2, name='new name0.9256307316798864'}
2016-06-21 10:45:51.895  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=3, name='new name0.20739161837680153'}
2016-06-21 10:45:51.896  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=4, name='new name0.765156925790501'}
2016-06-21 10:45:51.898  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=5, name='new name0.6248574362687188'}
2016-06-21 10:45:51.899  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=6, name='new name0.3790861546140586'}
2016-06-21 10:45:51.900  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=7, name='new name0.7495129902959482'}
2016-06-21 10:45:51.900  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Finished reading the stream. It should be closed by now.
2016-06-21 10:45:53.008  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=1, name='new name0.890543839463273'}
2016-06-21 10:45:53.011  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=2, name='new name0.9985621057806786'}
2016-06-21 10:45:53.013  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=3, name='new name0.30704881197334954'}
2016-06-21 10:45:53.014  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=4, name='new name0.1724676320279659'}
2016-06-21 10:45:53.016  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=5, name='new name0.8314101084743966'}
2016-06-21 10:45:53.018  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=6, name='new name0.0010605030255501635'}
2016-06-21 10:45:53.019  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Entity: TestEntity{id=7, name='new name0.8576828104281322'}
2016-06-21 10:45:53.019  INFO 34109 --- [pool-2-thread-1] my.test.TestEntityLogger                 : Finished reading the stream. It should be closed by now.
2016-06-21 10:45:53.211  WARN 34109 --- [:1466495148207]] o.a.tomcat.jdbc.pool.ConnectionPool      : Connection has been abandoned PooledConnection[conn9: url=jdbc:h2:mem:testdb user=SA]:java.lang.Exception
	at org.apache.tomcat.jdbc.pool.ConnectionPool.getThreadDump(ConnectionPool.java:1061)
	at org.apache.tomcat.jdbc.pool.ConnectionPool.borrowConnection(ConnectionPool.java:777)
	at org.apache.tomcat.jdbc.pool.ConnectionPool.borrowConnection(ConnectionPool.java:626)
	at org.apache.tomcat.jdbc.pool.ConnectionPool.getConnection(ConnectionPool.java:185)
	at org.apache.tomcat.jdbc.pool.DataSourceProxy.getConnection(DataSourceProxy.java:127)
	at org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl.getConnection(DatasourceConnectionProviderImpl.java:139)
	at org.hibernate.internal.AbstractSessionImpl$NonContextualJdbcConnectionAccess.obtainConnection(AbstractSessionImpl.java:380)
	at org.hibernate.engine.jdbc.internal.LogicalConnectionImpl.obtainConnection(LogicalConnectionImpl.java:228)
	at org.hibernate.engine.jdbc.internal.LogicalConnectionImpl.getConnection(LogicalConnectionImpl.java:171)
	at org.hibernate.engine.jdbc.internal.StatementPreparerImpl.connection(StatementPreparerImpl.java:63)
	at org.hibernate.engine.jdbc.internal.StatementPreparerImpl$5.doPrepare(StatementPreparerImpl.java:162)
	at org.hibernate.engine.jdbc.internal.StatementPreparerImpl$StatementPreparationTemplate.prepareStatement(StatementPreparerImpl.java:186)
	at org.hibernate.engine.jdbc.internal.StatementPreparerImpl.prepareQueryStatement(StatementPreparerImpl.java:160)
	at org.hibernate.loader.Loader.prepareQueryStatement(Loader.java:1885)
	at org.hibernate.loader.Loader.executeQueryStatement(Loader.java:1862)
	at org.hibernate.loader.Loader.executeQueryStatement(Loader.java:1839)
	at org.hibernate.loader.Loader.scroll(Loader.java:2627)
	at org.hibernate.loader.hql.QueryLoader.scroll(QueryLoader.java:561)
	at org.hibernate.hql.internal.ast.QueryTranslatorImpl.scroll(QueryTranslatorImpl.java:439)
	at org.hibernate.engine.query.spi.HQLQueryPlan.performScroll(HQLQueryPlan.java:355)
	at org.hibernate.internal.SessionImpl.scroll(SessionImpl.java:1381)
	at org.hibernate.internal.QueryImpl.scroll(QueryImpl.java:91)
	at org.springframework.data.jpa.provider.PersistenceProvider$HibernateScrollableResultsIterator.<init>(PersistenceProvider.java:414)
	at org.springframework.data.jpa.provider.PersistenceProvider$1.executeQueryWithResultStream(PersistenceProvider.java:118)
	at org.springframework.data.jpa.repository.query.JpaQueryExecution$StreamExecution.doExecute(JpaQueryExecution.java:323)
	at org.springframework.data.jpa.repository.query.JpaQueryExecution.execute(JpaQueryExecution.java:78)
	at org.springframework.data.jpa.repository.query.AbstractJpaQuery.doExecute(AbstractJpaQuery.java:100)
	at org.springframework.data.jpa.repository.query.AbstractJpaQuery.execute(AbstractJpaQuery.java:91)
	at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.doInvoke(RepositoryFactorySupport.java:462)
	at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.invoke(RepositoryFactorySupport.java:440)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
	at org.springframework.data.projection.DefaultMethodInvokingMethodInterceptor.invoke(DefaultMethodInvokingMethodInterceptor.java:61)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
	at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:99)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:281)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:136)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
	at org.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:131)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:208)
	at my.test.$Proxy55.findEntities(Unknown Source)
	at my.test.TestEntityLogger.logAllTestEntities(StreamErrorAppRunner.java:61)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at org.springframework.scheduling.support.ScheduledMethodRunnable.run(ScheduledMethodRunnable.java:65)
	at org.springframework.scheduling.support.DelegatingErrorHandlingRunnable.run(DelegatingErrorHandlingRunnable.java:54)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.runAndReset(FutureTask.java:308)
	at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$301(ScheduledThreadPoolExecutor.java:180)
	at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:294)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
```	

If you uncomment the `@Transactional` on line 59, then the connection is closed properly.