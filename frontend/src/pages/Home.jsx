import { Link } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

export function Home() {
    const { user } = useAuth();

    return (
        <div className="text-center py-20">
            <h1 className="text-5xl font-bold text-gray-900 mb-4">
                🏓 Torneios de Tênis de Mesa
            </h1>
            <p className="text-xl text-gray-600 mb-8">
                Organize e participe de campeonatos de tênis de mesa
            </p>
            {!user && (
                <div className="space-x-4">
                    <Link to="/register" className="btn-primary text-lg">
                        Começar Agora
                    </Link>
                    <Link to="/login" className="btn-secondary text-lg">
                        Já tenho conta
                    </Link>
                </div>
            )}
        </div>
    );
}