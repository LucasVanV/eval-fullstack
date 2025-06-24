export type Absence = {
	id?: number;
	jour: string;
};

export type Conges = {
	id?: number;
	dateDebut: string;
	dateFin: string;
};

export type Employe = {
	id: number;
	name: string;
	occupation: string;
	salaire: string;
	debutContrat: string;
	finContrat?: string | null;
	numeroIdentification: string;
	dateNaissance: string;
	adresse: string;
	email: string;
	telephone: string;
	observation?: string;
	absences?: Absence[];
	conges?: Conges[];
};

export type Candidat = {
	id: number;
	nom: string;
	numeroIdentification: string;
	adresse: string;
	dateNaissance: string;
	email: string;
	numeroTelephone: string;
	domaineTechnique: string;
	evaluation: number;
	dateEntretien?: string | null;
	observation?: string;
};
