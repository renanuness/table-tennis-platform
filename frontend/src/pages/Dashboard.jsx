import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import api from '../services/api';

export function Dashboard() {
    const { user } = useAuth();
    const [upcomingTournaments, setUpcomingTournaments] = useState([]);
    const [myTournaments, setMyTournaments] = useState([]);
    const [stats, setStats] = useState({
        totalTournaments: 0,
        activeTournaments: 0,
        totalParticipants: 0
    });
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        loadDashboardData();
    }, []);

    const loadDashboardData = async () => {
        try {
            const [upcomingRes, myTournamentsRes, statsRes] = await Promise.all([
                api.get('/tournaments?status=OPEN'),
                api.get('/tournaments/my'),
                api.get('/tournaments/stats')
            ]);

            setUpcomingTournaments(upcomingRes.data.slice(0, 3)); // Últimos 3
            setMyTournaments(myTournamentsRes.data.slice(0, 3)); // Últimos 3
            setStats(statsRes.data);
        } catch (error) {
            console.error('Erro ao carregar dashboard:', error);
        } finally {
            setLoading(false);
        }
    };

    if (loading) {
        return (
            <div className="flex justify-center items-center min-h-[60vh]">
                <div className="text-center">
                    <div className="animate-spin text-4xl mb-4">🏓</div>
                    <p className="text-gray-500">Carregando...</p>
                </div>
            </div>
        );
    }

    return (
        <div className="max-w-7xl mx-auto">
            {/* Boas-vindas */}
            <div className="mb-8">
                <h1 className="text-3xl font-bold text-gray-900">
                    Olá, {user?.nome?.split(' ')[0]}! 👋
                </h1>
                <p className="text-gray-600 mt-2">
                    Pronto para mais um torneio de tênis de mesa?
                </p>
            </div>

            {/* Cards de Estatísticas */}
            <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
                <div className="card bg-gradient-to-br from-blue-500 to-blue-600 text-white">
                    <div className="flex items-center justify-between">
                        <div>
                            <p className="text-blue-100 text-sm">Total de Torneios</p>
                            <p className="text-3xl font-bold">{stats.totalTournaments}</p>
                        </div>
                        <span className="text-4xl">🏆</span>
                    </div>
                </div>

                <div className="card bg-gradient-to-br from-green-500 to-green-600 text-white">
                    <div className="flex items-center justify-between">
                        <div>
                            <p className="text-green-100 text-sm">Torneios Ativos</p>
                            <p className="text-3xl font-bold">{stats.activeTournaments}</p>
                        </div>
                        <span className="text-4xl">🎯</span>
                    </div>
                </div>

                <div className="card bg-gradient-to-br from-purple-500 to-purple-600 text-white">
                    <div className="flex items-center justify-between">
                        <div>
                            <p className="text-purple-100 text-sm">Participantes</p>
                            <p className="text-3xl font-bold">{stats.totalParticipants}</p>
                        </div>
                        <span className="text-4xl">👥</span>
                    </div>
                </div>
            </div>

            {/* Ações Rápidas */}
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-8">
                <Link
                    to="/tournaments/new"
                    className="card hover:shadow-lg transition-shadow group"
                >
                    <div className="flex items-center space-x-4">
                        <div className="bg-blue-100 p-3 rounded-lg group-hover:bg-blue-200 transition-colors">
                            <span className="text-2xl">➕</span>
                        </div>
                        <div>
                            <h3 className="font-semibold text-gray-900">Criar Torneio</h3>
                            <p className="text-sm text-gray-600">Organize um novo campeonato</p>
                        </div>
                    </div>
                </Link>

                <Link
                    to="/tournaments"
                    className="card hover:shadow-lg transition-shadow group"
                >
                    <div className="flex items-center space-x-4">
                        <div className="bg-green-100 p-3 rounded-lg group-hover:bg-green-200 transition-colors">
                            <span className="text-2xl">🔍</span>
                        </div>
                        <div>
                            <h3 className="font-semibold text-gray-900">Explorar Torneios</h3>
                            <p className="text-sm text-gray-600">Encontre torneios para participar</p>
                        </div>
                    </div>
                </Link>
            </div>

            {/* Próximos Torneios */}
            <div className="mb-8">
                <div className="flex justify-between items-center mb-4">
                    <h2 className="text-xl font-bold text-gray-900">
                        📅 Próximos Torneios
                    </h2>
                    <Link
                        to="/tournaments"
                        className="text-blue-600 hover:underline text-sm"
                    >
                        Ver todos →
                    </Link>
                </div>

                {upcomingTournaments.length === 0 ? (
                    <div className="card text-center py-8">
                        <span className="text-4xl mb-3 block">🏓</span>
                        <p className="text-gray-500">Nenhum torneio disponível no momento</p>
                        <Link
                            to="/tournaments/new"
                            className="btn-primary mt-4 inline-block"
                        >
                            Criar primeiro torneio
                        </Link>
                    </div>
                ) : (
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                        {upcomingTournaments.map(tournament => (
                            <Link
                                key={tournament.id}
                                to={`/tournaments/${tournament.id}`}
                                className="card hover:shadow-lg transition-shadow"
                            >
                                <div className="flex items-center justify-between mb-3">
                                    <span className="bg-green-100 text-green-800 text-xs px-2 py-1 rounded-full">
                                        {tournament.status}
                                    </span>
                                    <span className="text-sm text-gray-500">
                                        {new Date(tournament.date).toLocaleDateString('pt-BR')}
                                    </span>
                                </div>
                                <h3 className="font-semibold text-gray-900 mb-2">
                                    {tournament.name}
                                </h3>
                                <div className="flex items-center text-sm text-gray-600 space-x-4">
                                    <span>📍 {tournament.place}</span>
                                    <span>
                                        👥 {tournament.currentParticipants}/{tournament.maxParticipants}
                                    </span>
                                </div>
                            </Link>
                        ))}
                    </div>
                )}
            </div>

            {/* Meus Torneios */}
            <div>
                <div className="flex justify-between items-center mb-4">
                    <h2 className="text-xl font-bold text-gray-900">
                        🎯 Meus Torneios
                    </h2>
                    <Link
                        to="/tournaments/my"
                        className="text-blue-600 hover:underline text-sm"
                    >
                        Ver todos →
                    </Link>
                </div>

                {myTournaments.length === 0 ? (
                    <div className="card text-center py-8">
                        <span className="text-4xl mb-3 block">🎾</span>
                        <p className="text-gray-500">Você ainda não participa de nenhum torneio</p>
                        <Link
                            to="/tournaments"
                            className="btn-secondary mt-4 inline-block"
                        >
                            Encontrar torneios
                        </Link>
                    </div>
                ) : (
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                        {myTournaments.map(tournament => (
                            <Link
                                key={tournament.id}
                                to={`/tournaments/${tournament.id}`}
                                className="card hover:shadow-lg transition-shadow"
                            >
                                <div className="flex items-center justify-between mb-3">
                                    <span className="bg-blue-100 text-blue-800 text-xs px-2 py-1 rounded-full">
                                        {tournament.status}
                                    </span>
                                    <span className="text-sm text-gray-500">
                                        {new Date(tournament.date).toLocaleDateString('pt-BR')}
                                    </span>
                                </div>
                                <h3 className="font-semibold text-gray-900 mb-2">
                                    {tournament.name}
                                </h3>
                                <p className="text-sm text-gray-600">
                                    Organizador: {tournament.organizerName}
                                </p>
                            </Link>
                        ))}
                    </div>
                )}
            </div>
        </div>
    );
}