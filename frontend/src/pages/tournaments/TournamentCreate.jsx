import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import api from '../../services/api';

export function TournamentCreate() {
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
            const response = await api.post('/tournaments', data);
            navigate(`/tournaments/${response.data.id}`);
        } catch (err) {
            setError(err.response?.data?.message || 'Erro ao criar torneio');
        }
    };

    return (
        <div className="max-w-2xl mx-auto">
            <h1 className="text-3xl font-bold mb-8">Criar Novo Torneio</h1>

            <div className="card">
                {error && (
                    <div className="bg-red-50 text-red-600 p-3 rounded-lg mb-4">
                        {error}
                    </div>
                )}

                <form onSubmit={handleSubmit(onSubmit)}>
                    <div className="mb-4">
                        <label className="block text-sm font-medium text-gray-700 mb-1">
                            Nome do Torneio
                        </label>
                        <input
                            {...register('name', {
                                required: 'Nome é obrigatório',
                                minLength: { value: 3, message: 'Mínimo 3 caracteres' }
                            })}
                            className="input-field"
                            placeholder="Ex: Campeonato de Verão 2026"
                        />
                        {errors.name && (
                            <p className="mt-1 text-sm text-red-600">{errors.name.message}</p>
                        )}
                    </div>

                    <div className="grid grid-cols-2 gap-4">
                        <div className="mb-4">
                            <label className="block text-sm font-medium text-gray-700 mb-1">
                                Data
                            </label>
                            <input
                                {...register('date', { required: 'Data é obrigatória' })}
                                className="input-field"
                                type="date"
                            />
                            {errors.date && (
                                <p className="mt-1 text-sm text-red-600">{errors.date.message}</p>
                            )}
                        </div>

                        <div className="mb-4">
                            <label className="block text-sm font-medium text-gray-700 mb-1">
                                Máximo de Participantes
                            </label>
                            <input
                                {...register('maxParticipants', {
                                    required: 'Número é obrigatório',
                                    min: { value: 2, message: 'Mínimo 2' },
                                    max: { value: 128, message: 'Máximo 128' }
                                })}
                                className="input-field"
                                type="number"
                            />
                            {errors.maxParticipants && (
                                <p className="mt-1 text-sm text-red-600">{errors.maxParticipants.message}</p>
                            )}
                        </div>
                    </div>

                    <div className="mb-6">
                        <label className="block text-sm font-medium text-gray-700 mb-1">
                            Local
                        </label>
                        <input
                            {...register('place', { required: 'Local é obrigatório' })}
                            className="input-field"
                            placeholder="Ex: Ginásio Municipal"
                        />
                        {errors.place && (
                            <p className="mt-1 text-sm text-red-600">{errors.place.message}</p>
                        )}
                    </div>

                    <div className="flex space-x-4">
                        <button
                            type="submit"
                            disabled={isSubmitting}
                            className="btn-primary flex-1"
                        >
                            {isSubmitting ? 'Criando...' : 'Criar Torneio'}
                        </button>
                        <button
                            type="button"
                            onClick={() => navigate(-1)}
                            className="btn-secondary"
                        >
                            Cancelar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}