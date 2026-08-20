// src/pages/Register.jsx
import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

export function Register() {
    const { register: registerUser } = useAuth();
    const navigate = useNavigate();
    const [error, setError] = useState('');
    const {
        register,
        handleSubmit,
        formState: { errors, isSubmitting }
    } = useForm();

    const onSubmit = async (data) => {
        try {
            setError('');
            await registerUser(data);
            navigate('/login');
        } catch (err) {
            setError(err.response?.data?.message || 'Erro ao cadastrar');
        }
    };

    return (
        <div className="max-w-md mx-auto mt-10">
            <div className="card">
                <h1 className="text-2xl font-bold mb-6">Criar Conta</h1>

                {error && (
                    <div className="bg-red-50 text-red-600 p-3 rounded-lg mb-4">
                        {error}
                    </div>
                )}

                <form onSubmit={handleSubmit(onSubmit)}>
                    <div className="mb-4">
                        <label className="block text-sm font-medium text-gray-700 mb-1">
                            Nome
                        </label>
                        <input
                            {...register('nome', {
                                required: 'Nome é obrigatório',
                                minLength: { value: 3, message: 'Mínimo 3 caracteres' }
                            })}
                            className="input-field"
                            placeholder="Seu nome completo"
                        />
                        {errors.nome && (
                            <p className="mt-1 text-sm text-red-600">{errors.nome.message}</p>
                        )}
                    </div>

                    <div className="mb-4">
                        <label className="block text-sm font-medium text-gray-700 mb-1">
                            Email
                        </label>
                        <input
                            {...register('email', {
                                required: 'Email é obrigatório',
                                pattern: {
                                    value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                                    message: 'Email inválido'
                                }
                            })}
                            className="input-field"
                            type="email"
                            placeholder="seu@email.com"
                        />
                        {errors.email && (
                            <p className="mt-1 text-sm text-red-600">{errors.email.message}</p>
                        )}
                    </div>

                    <div className="mb-6">
                        <label className="block text-sm font-medium text-gray-700 mb-1">
                            Senha
                        </label>
                        <input
                            {...register('senha', {
                                required: 'Senha é obrigatória',
                                minLength: { value: 6, message: 'Mínimo 6 caracteres' }
                            })}
                            className="input-field"
                            type="password"
                            placeholder="••••••"
                        />
                        {errors.senha && (
                            <p className="mt-1 text-sm text-red-600">{errors.senha.message}</p>
                        )}
                    </div>

                    <button
                        type="submit"
                        disabled={isSubmitting}
                        className="btn-primary w-full"
                    >
                        {isSubmitting ? 'Cadastrando...' : 'Cadastrar'}
                    </button>
                </form>

                <p className="mt-4 text-center text-gray-600">
                    Já tem conta?{' '}
                    <Link to="/login" className="text-blue-600 hover:underline">
                        Entrar
                    </Link>
                </p>
            </div>
        </div>
    );
}