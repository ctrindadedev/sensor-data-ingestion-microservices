package com.sensor_data_ingestion.operations_service.domain.either;

import java.util.function.Function;

public sealed interface Either<L, R> permits Either.Left, Either.Right {

    record Left<L, R>(L value) implements Either<L, R> {}
    record Right<L, R>(R value) implements Either<L, R> {}

    static <L, R> Either<L, R> left(L value)  { return new Left<>(value); }
    static <L, R> Either<L, R> right(R value) { return new Right<>(value); }

    // Para transformar o dado quando NÃO há risco de falha
    default <T> Either<L, T> map(Function<R, T> mapper) {
        return switch (this) {
            case Left<L, R>  l -> Either.left(l.value());
            case Right<L, R> r -> Either.right(mapper.apply(r.value()));
        };
    }

    // Para encadear regras de negócio que também podem falhar
    default <T> Either<L, T> flatMap(Function<R, Either<L, T>> mapper) {
        return switch (this) {
            case Left<L, R>  l -> Either.left(l.value());
            case Right<L, R> r -> mapper.apply(r.value());
        };
    }

    // Porta de sáida do dominio
    default <T> T fold(Function<? super L, ? extends T> mapperLeft,
                       Function<? super R, ? extends T> mapperRight) {
        return switch (this) {
            case Left<L, R> l -> mapperLeft.apply(l.value());
            case Right<L, R> r -> mapperRight.apply(r.value());
        };
    }
}